package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ02156_포도주시식 {
    private static int n;
    private static int[] wine;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        wine = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(findMax());
    }

    private static int findMax() {
        int[][] dp = new int[n + 2][4];
        for (int i = 2; i <= n + 1; i++) {
            // 먹고 먹고
            dp[i][0] = Math.max(Math.max(dp[i - 2][1], dp[i - 2][2]), dp[i - 2][3]) + wine[i] + wine[i - 1];

            // 먹고 뛰고
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + wine[i - 1];

            // 뛰고
            dp[i][2] = Math.max(Math.max(dp[i - 1][1], dp[i - 1][2]), dp[i - 1][0]);
        }
        //System.out.println(Arrays.deepToString(dp));

        return Math.max(Math.max(dp[n + 1][1], dp[n + 1][2]), dp[n + 1][0]);
    }
}
