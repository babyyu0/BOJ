package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ11057_오르막수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 오르막 수
        long[][] dp = new long[N + 1][10];
        Arrays.fill(dp[1], 1);

        for (int i = 2; i <= N; i++) {  // 자리 수
            for (int j = 0; j < 10; j++) {  // 수
                dp[i][j] = dp[i][Math.max(j - 1, 0)] + dp[i - 1][j];
                dp[i][j] %= 10007;
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = (result + dp[N][i]) % 10007;
        }

        //System.out.println(Arrays.deepToString(dp).replace("], ", "]\n"));
        System.out.println(result);
    }
}
