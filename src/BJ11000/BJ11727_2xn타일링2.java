package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11727_2xn타일링2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        dp[1] = 1;
        if(N == 1) {
            System.out.println(1);
            return;
        }
        dp[2] = 3;
        if(N == 2) {
            System.out.println(3);
            return;
        }

        // dp[1] = 1
        // dp[2] = 3
        // dp[3] = 5
        // dp[4] = 11
        // dp[5] = 20

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + (dp[i - 2] * 2)) % 10007;
        }
        System.out.println(dp[N]);
    }
}
