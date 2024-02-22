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

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    private static int N;
    private static long MIN_MULTIPLY;  // 행렬의 개수
    private static Pos[] poses;  // 행렬을 담을 배열
    private static long[][] cal, dp;  // 각 곱을 더할 배열


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        MIN_MULTIPLY = (long) Math.pow(2, 31);
        N = Integer.parseInt(br.readLine());
        poses = new Pos[N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], (long) Math.pow(2, 31));
            dp[i][i] = 0;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            poses[i] = new Pos(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));  // 행렬 담기
        }

        System.out.println(Arrays.deepToString(dp).replace("],", "]\n") + "\n");
        findMinMultiply();
        System.out.println(Arrays.deepToString(dp).replace("],", "]\n") + "\n");

        System.out.println(dp[0][N - 1]);
    }

    private static void findMinMultiply() {
        for (int k = 0; k <= N; k++) {
            for (int i = 1; i < N; i++) {  // i까지
                for (int j = i - 1; j >= 0; j--) {  // j에서
                    System.out.print("dp[" + (char)(j + 65) + "][" + (char)(i + 65) + "] = dp[" + (char)(j + 65) + "][" + (char)(i + 64) + "] + " + (poses[j].r * poses[i].r * poses[i].c));
                    dp[j][i] = dp[j][i - 1] + poses[j].r * poses[i].r * poses[i].c;

                    System.out.println(" || dp[" + (char)(j + 66) + "][" + (char)(i + 65) + "] + " + (poses[j].r * poses[j].c * poses[i].c));
                    dp[j][i] = Math.min(poses[j].r * poses[j].c * poses[i].c + dp[j + 1][i], dp[j][i]);
                    System.out.println("dp[" + (char)(j + 65) + "][" + (char)(i + 65) + "] = " + dp[j][i] );
                }
            }
            //System.out.println(Arrays.deepToString(dp).replace("],", "]\n") + "\n");
        }
    }
}
