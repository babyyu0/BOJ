package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ02133_타일채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        if(N % 2 == 1) {
            System.out.println(0);
            System.exit(0);
        }
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= N; i+=2) {
            dp[i] = dp[i - 2] * 4 - dp[i - 4];
        }

        System.out.println(dp[N]);
    }
}
