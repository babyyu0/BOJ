package BJ01000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1012_유기농배추 {
    private static class Pos {
        Pos(int r, int c) {
            this.r = r; this.c = c;
        }
        int r, c;
    }
    private static int T, N, M, K, count, r, c;
    private static int[][] map;
    private static Queue<Pos> queue;
    private static Pos curPos;

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        queue = new ArrayDeque<>();

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                c = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                map[r][c] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] == 1) bfs(new Pos(i, j));
                }
            }

            sb.append(count).append("\n");
            count = 0;
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(Pos pos) {
        queue.clear();
        map[pos.r][pos.c] = 0;
        queue.add(pos);

        count++;
        while (!queue.isEmpty()) {
            curPos = queue.poll();
            for (int i = 0; i < 4; i++) {
                try {
                    if (map[curPos.r + dr[i]][curPos.c + dc[i]] == 1) {
                        map[curPos.r + dr[i]][curPos.c + dc[i]] = 0;
                        queue.add(new Pos(curPos.r + dr[i], curPos.c + dc[i]));
                    }
                } catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
    }
}
