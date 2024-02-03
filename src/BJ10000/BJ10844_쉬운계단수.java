package BJ10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ10844_쉬운계단수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        long[][] dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);
        dp[1][0] = 0;
        for (int i = 2; i <= N; i++) {  // 자리수
            for (int j = 0; j < 10; j++) {
                //dp[i][j] += dp[i][Math.max(j - 1, 0)];
                if(0 <= j - 1) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if(j + 1 < 10) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= 1000000000;
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i];
        }

        ///System.out.println(Arrays.deepToString(dp).replace("],", "]\n"));
        System.out.println(sum % 1000000000);
    }
}
