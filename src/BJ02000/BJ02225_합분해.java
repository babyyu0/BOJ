package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02225_합분해 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        long[][] dp = new long[N + 1][K + 1];

        for (int i = 1; i <= K; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= N; i++) {  // 수
            for (int j = 1; j <= K; j++) {  // 개수
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
            }
        }

        //System.out.println(Arrays.deepToString(dp).replace("],", "]\n"));
        System.out.println(dp[N][K]);
    }
}
