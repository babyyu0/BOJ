package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11052_카드구매하기 {
    private static class Card {
        public Card(int count, double price) {
            this.count = count;
            this.price = price;
        }

        int count;
        double price;
    }

    private static int N;  // 카드의 개수
    private static Card[] cards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int buy = N;
        long money = 0;
        cards = new Card[N];

        /*
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = new Card(i + 1, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(cards, (o1, o2) -> Double.compare(o2.price / o2.count, o1.price / o1.count));

        for (int i = 0; i < N; i++) {
            if(cards[i].count <= buy) {
                //System.out.println(cards[i] + " 카드 " + Math.floor(buy / cards[i].count) + "개 사기");
                money += (cards[i].price * Math.floor(buy / cards[i].count));
                buy %= cards[i].count;
            }
        }

        System.out.println(money);
        */

        cards = new Card[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cards[i] = new Card(i + 1, Integer.parseInt(st.nextToken()));
        }

        long[][] dp = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {  // 카드 개수에 맞춰 계산 하기
            for (int j = 1; j <= N; j++) {  // 금액에 맞춰 계산 하기
                if(j % i == 0) {
                    dp[i][j] = (long) (cards[i].price * (j / i));
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
