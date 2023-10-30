package BJ12000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ12865_평범한배낭 {
    private static int N, K;
    private static int[][] stuff, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 물품의 수
        K = Integer.parseInt(st.nextToken());  // 버틸 수 있는 무게
        stuff = new int[N + 1][2];
        dp = new int[N + 1][K + 1];  // 무게 당 들어갈 수 있는 짐

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            stuff[i][0] = Integer.parseInt(st.nextToken());  // 크기
            stuff[i][1] = Integer.parseInt(st.nextToken());  // 무게
        }

        Arrays.sort(stuff, Comparator.comparingInt(o -> o[0]));  // 무게 별 정렬

        dp();
        System.out.println(dp[N][K]);
    }

    private static void dp() {
        for (int i = 1; i <= N; i++) {  // 짐마다 순회
            for (int j = 1; j <= K; j++) {  // 들 수 있는 짐
                // 현재 짐과 들 수 있는 짐 비교
                if(stuff[i][0] <= j) dp[i][j] = stuff[i][1] + dp[i - 1][j - stuff[i][0]];
                // 들 수 있는 최대 짐과 크기 비교
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);

            }
            // System.out.println(i + ": " + Arrays.toString(dp[i]));
        }
    }
}
