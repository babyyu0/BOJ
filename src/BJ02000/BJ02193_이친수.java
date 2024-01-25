package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ02193_이친수 {
    private static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];
        dp[1] = 1;
        if(N <= 1) {
            System.out.println(dp[N]);
            return;
        }

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[N]);
    }
}
// 1000 1001 1010
// 10000 10001 10010 10100 10101
// 100000 100001 100010 100100 101000 100101 101001 101010
