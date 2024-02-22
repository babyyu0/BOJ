package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11049_행렬곱셈순서 {
    private static class Pos {
        public Pos(long r, long c) {
            this.r = r;
            this.c = c;
        }

        long r, c;
    }

    private static int N;
    private static Pos[] poses;  // 행렬을 담을 배열
    private static long[][] dp;  // 각 곱을 더할 배열


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        poses = new Pos[N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], (long) Math.pow(2, 31));
            st = new StringTokenizer(br.readLine());
            poses[i] = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));  // 행렬 담기
        }

        System.out.println(findMinMultiply());
    }

    private static long findMinMultiply() {
        for (int i = 0; i < N; i++) { // i 까지
            for (int j = i - 1; 0 <= j; j--) {  // j 에서
                if (i - j == 1) {  // 무조건 결과 하나 (AB) 같은 경우
                    dp[j][i] = (poses[j].r * poses[j].c * poses[i].c);
                    continue;
                }

                dp[j][i] = Math.min(dp[j][i - 1] + (poses[j].r * poses[i].r * poses[i].c), dp[j][i]);  // (...) * n 값 찾기 ((AB)C) 같은 경우
                dp[j][i] = Math.min((poses[j].r * poses[j].c * poses[i].c) + dp[j + 1][i], dp[j][i]);  // n * (...) 값 찾기 (A(BC)) 같은 경우

                for (int k = j + 1; k < i - 1; k++) {  // 사이 계산 찾기 ((AB)(CD)) 같은 경우
                    dp[j][i] = Math.min(dp[j][k] + dp[k + 1][i] + (poses[j].r * poses[k].c * poses[i].c), dp[j][i]);
                }

            }

        }

        return dp[0][N - 1];
    }
}
