package BJ17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17404_RGB거리2 {
    private static int N, MIN; // 집 개수
    private static final int HOUSE_CNT = 3;  // 색 개수
    private static int[][] costs;
    private static int[][][] dp;  // 집 비용을 더할 2차원배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costs = new int[N][HOUSE_CNT];
        dp = new int[HOUSE_CNT][N][HOUSE_CNT];
        MIN = 1000000;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < HOUSE_CNT; j++) {
                Arrays.fill(dp[j][i], 1000000);
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < HOUSE_CNT; i++) {
            for (int j = 0; j < HOUSE_CNT; j++) {
                if (i == j) {
                    dp[i][0][j] = costs[0][j];
                    continue;
                }
                dp[i][0][j] = 1000000;
            }
            findMin(i);
        }
        System.out.println(MIN);
    }

    private static void findMin(int start) {
        for (int i = 1; i < N - 1; i++) {
            for (int j = 0; j < HOUSE_CNT; j++) {
                for (int k = 0; k < HOUSE_CNT; k++) {
                    if (j == k) continue;
                    dp[start][i][j] = Math.min(dp[start][i][j], dp[start][i - 1][k] + costs[i][j]);
                }
            }
        }

        for (int i = 0; i < HOUSE_CNT; i++) {
            for (int j = 0; j < HOUSE_CNT; j++) {
                if (j != start && i != j) {
                    dp[start][N - 1][j] = Math.min(dp[start][N - 1][j], dp[start][N - 2][i] + costs[N - 1][j]);
                    MIN = Math.min(MIN, dp[start][N - 1][j]);
                }
            }
        }

        //System.out.println(Arrays.deepToString(dp).replace("],", "]\n"));
    }
}
