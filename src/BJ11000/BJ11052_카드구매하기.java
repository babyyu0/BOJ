package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11052_카드구매하기 {

    private static int N;  // 카드의 개수
    private static long[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cards = new long[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cards[i] = Long.parseLong(st.nextToken());
        }

        long[][] dp = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {  // 카드 개수에 맞춰 계산 하기
            for (int j = 1; j <= N; j++) {  // 금액에 맞춰 계산 하기
                if(j % i == 0) {
                    dp[i][j] = cards[i] * (j / i);
                }
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i - 1][j - k]);
                }
            }
        }

        //System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[N][N]);
    }
}
