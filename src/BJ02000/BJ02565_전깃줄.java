package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ02565_전깃줄 {
    private static class Wire {
        public Wire(int leftNum, int rightNum) {
            this.leftNum = leftNum;
            this.rightNum = rightNum;
        }

        int leftNum, rightNum;
    }

    private static int N;
    private static Wire[] wires;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        wires = new Wire[N];

        int leftNum, rightNum;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            leftNum = Integer.parseInt(st.nextToken());
            rightNum = Integer.parseInt(st.nextToken());

            wires[i] = new Wire(leftNum, rightNum);
        }

        Arrays.sort(wires, Comparator.comparingInt(o -> o.leftNum));
        System.out.println(N - findAvailableWire());
    }

    private static int findAvailableWire() {
        int[] availableSum = new int[N];
        int max = 0;

        for (int i = 0; i < N; i++) {
            availableSum[i] = 1;
            for (int j = 0; j < i; j++) {
                if ((wires[i].leftNum < wires[j].leftNum && wires[i].rightNum > wires[j].rightNum)
                        || (wires[i].leftNum > wires[j].leftNum && wires[i].rightNum < wires[j].rightNum)) {  // 교차될 경우
                    continue;
                }

                availableSum[i] = Math.max(availableSum[j] + 1, availableSum[i]);
                max = Math.max(max, availableSum[i]);
            }
        }

        //System.out.println(Arrays.toString(availableSum));
        return max;
    }
}
