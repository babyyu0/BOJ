package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] sumMap = new int[N + 1];

        int MIN_RANGE = N + 1, start = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sumMap[i] = sumMap[i - 1] + Integer.parseInt(st.nextToken());

            while(S <= sumMap[i] - sumMap[start]) {
                //System.out.println("현재 인덱스 " + start + "부터 " + i + "까지일 때, 총 합 " + (sumMap[i] - sumMap[start]));
                MIN_RANGE = Math.min(MIN_RANGE, (i - start));
                start++;
            }
        }

        //System.out.println(Arrays.deepToString(sumMap).replace("],", "]\n"));
        System.out.println(N < MIN_RANGE ? 0 : MIN_RANGE);
    }
}
