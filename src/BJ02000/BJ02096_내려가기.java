package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ02096_내려가기 {
    private static int N, MAX, MIN;
    private static int[][] score, maxDp, minDp;
    private static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        score = new int[N + 1][3];
        maxDp = new int[N + 1][3];
        minDp = new int[N + 1][3];
        MIN = 9 * 100000 + 1;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(minDp[i], 9 * 100000 + 1);
            for (int j = 0; j < 3; j++) {
                score[i][j] = Integer.parseInt(st.nextToken());  // 점수 삽입
            }
        }

        dp();

        for (int i = 0; i < 3; i++) {
            MAX = Math.max(MAX, maxDp[N][i]);
            MIN = Math.min(MIN, minDp[N][i]);
        }

        System.out.println(MAX + " " + MIN);
    }

    private static void dp() {
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(0 <= j + dx[k] && j + dx[k] < 3) {
                        maxDp[i][j] = Math.max(maxDp[i][j], score[i][j] + maxDp[i - 1][j + dx[k]]);
                        minDp[i][j] = Math.min(minDp[i][j], score[i][j] + minDp[i - 1][j + dx[k]]);
                    }
                }
            }
            // System.out.println(i + ": " + Arrays.toString(maxDp[i]));
            // System.out.println(i + ": " + Arrays.toString(minDp[i]));
        }
    }
}
