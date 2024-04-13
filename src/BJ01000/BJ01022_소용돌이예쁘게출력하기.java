package BJ01000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ01022_소용돌이예쁘게출력하기 {
    private static int N, M;
    private static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        N -= n;
        M -= m;
        N++;
        M++;
        map = new long[N][M];

        long num = 0;
        int r = -n;
        int c = -m;

        int count = 1;
        long numCount = 0;
        while (numCount < (long) N * M) {
            // 오른쪽
            for (int i = c; i < c + count; i++) {
                num++;
                if (M <= i || i < 0 || r < 0 || N <= r) continue;
                numCount++;
                map[r][i] = num;
            }
            for (int i = r; i > r - count; i--) {
                num++;
                if (i < 0 || N <= i || c + count < 0 || M <= c + count) continue;
                numCount++;
                map[i][c + count] = num;
            }
            for (int i = c + count; i >= c; i--) {
                num++;
                if (i < 0 || M <= i || r - count < 0 || N <= r - count) continue;
                numCount++;
                map[r - count][i] = num;
            }
            for (int i = r - count; i <= r; i++) {
                num++;
                if (i < 0 || N <= i || c - 1 < 0 || M <= c - 1) continue;
                numCount++;
                map[i][c - 1] = num;
            }
            count += 2;
            r = r + 1;
            c = c - 1;
        }

        int form = Long.toString(map[0][0]).length();
        form = Math.max(form, Long.toString(map[N - 1][0]).length());
        form = Math.max(form, Long.toString(map[0][M - 1]).length());
        form = Math.max(form, Long.toString(map[N - 1][M - 1]).length());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(String.format("%" + form + "d ", map[i][j]));
            }
            sb.append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
