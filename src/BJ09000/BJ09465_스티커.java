package BJ09000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ09465_스티커 {
    private static int T, N;  // 테스트케이스, 스티커의 행
    private static int[][] stikers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());
            stikers = new int[2][N + 1];

            // 스티커 값 삽입
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    stikers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(dp(new int[2][N + 1])).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int dp(int[][] sum) {
        sum[0][1] = stikers[0][1];
        sum[1][1] = stikers[1][1];

        for (int i = 2; i <= N; i++) {
            sum[0][i] = Math.max(sum[1][i - 1], sum[1][i - 2]) + stikers[0][i];
            sum[1][i] = Math.max(sum[0][i - 1], sum[0][i - 2]) + stikers[1][i];
        }

        // System.out.println(Arrays.deepToString(stikers).replace("],", "\n"));
        // System.out.println(Arrays.deepToString(sum).replace("],", "\n"));

        return Math.max(sum[0][N], sum[1][N]);
    }
}
