package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02293_동전1 {
    private static int n, k;  // 동전 종류, 가치의 합
    private static int[] price;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        price = new int[n + 1];
        dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            price[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {  // 숫자
            for (int j = 1; j <= k; j++) {  // 숫자의 합
                dp[i][j] += dp[i - 1][j];
                if (j % price[i] == 0) dp[i][j]++;
                for (int l = price[i]; l < j; l += price[i]) {  // 숫자를 더할 수 있을 경우
                    dp[i][j] += dp[i - 1][j - l];
                }

            }
        }

        //System.out.println(Arrays.deepToString(dp).replace("], ", "]\n"));
        System.out.println(dp[n][k]);
    }
}
