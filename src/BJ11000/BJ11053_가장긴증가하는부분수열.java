package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11053_가장긴증가하는부분수열 {

    private static class Num {
        private Num(int index, int num, int depth) {
            this.index = index; this.num = num; this.depth = depth;
        }
        int index, num, depth;

        @Override
        public String toString() {
            return "Num{" +
                    "index=" + index +
                    ", num=" + num +
                    ", depth=" + depth +
                    '}';
        }
    }
    private static int N, max;
    private static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());  // 숫자 삽입
        }

        findMax();  // BFS 함수 호출
        System.out.println(max);
    }

    private static void findMax() {
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = i - 1; 0 <= j; j--) {
                if(nums[j] < nums[i]) {
                    sum[i] = Math.max(sum[j] + 1, sum[i]);
                    max = Math.max(max, sum[i]);
                }
            }
        }

        // System.out.println(Arrays.toString(sum));
    }
}
