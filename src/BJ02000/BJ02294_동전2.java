package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ02294_동전2 {
    private static int n, k;
    private static Integer[] price;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());  // 동전 종류
        k = Integer.parseInt(st.nextToken());  // 만들어야 하는 합
        //price = new int[n + 1];
        Set<Integer> set = new HashSet<>();
        set.add(0);

        for (int i = 1; i <= n; i++) {
            set.add(Integer.parseInt(br.readLine()));
            //price[i] = Integer.parseInt(br.readLine());
        }
        n = set.size() - 1;
        price = set.toArray(new Integer[0]);
        Arrays.sort(price);
        dp = new int[n + 1][k + 1];

        findMin();
        //System.out.println(Arrays.deepToString(dp).replace("],", "]\n"));
        System.out.println(10000 < dp[n][k] ? -1 : dp[n][k]);
    }

    private static void findMin() {
        int tmp;
        Arrays.fill(dp[0], 10001);
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], 10001);
            for (int j = 1; j <= k; j++) {
                if (j % price[i] == 0) {
                    dp[i][j] = j / price[i];
                }
                for (int l = 1; price[i] * l <= j; l++) {
                    tmp = dp[i - 1][j - (price[i] * l)];
                    if (0 < tmp) {  // 상응하는 금액이 존재할 경우
                        dp[i][j] = Math.min(dp[i][j], l + tmp);
                    }
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
            }
        }
    }
}
