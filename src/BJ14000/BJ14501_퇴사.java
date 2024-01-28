package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501_퇴사 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        StringTokenizer st;
        int T, P;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            if(i + T <= N) {
                for (int j = i + T; j <= N; j++) {
                    dp[j] = Math.max(dp[i] + P, dp[j]);
                }
            }
        }
        System.out.println(dp[N]);

    }
}
