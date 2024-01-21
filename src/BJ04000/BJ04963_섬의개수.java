package BJ04000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ04963_섬의개수 {
    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }
    private static int w, h;
    private static boolean[][] map;
    private static Queue<Pos> queue;
    private static int[] dr = {-1, 1, 0, 0, -1, 1, 1, -1}, dc = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        queue = new ArrayDeque<>();
        int COUNT;

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new boolean[h][w];
        while(w != 0 || h != 0) {  // 높이와 너비가 0이 아닐때까지 반복
            COUNT = 0;  // COUNT 0으로 초기화
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = (st.nextToken().charAt(0) == '1');
                    if(map[i][j]) {
                        queue.add(new Pos(i, j));
                    }
                }
            }  // map 저장

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(map[i][j]) {
                        COUNT++;
                        map[i][j] = false;
                        bfs(new Pos(i, j));
                    }
                }
            }

            sb.append(COUNT).append("\n");

            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new boolean[h][w];
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(Pos pos) {
        queue.clear();
        queue.add(pos);

        int mr, mc;
        while(!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 8; i++) {
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];
                if(0 <= mr && mr < h && 0 <= mc && mc < w && map[mr][mc]) {
                    map[mr][mc] = false;
                    queue.add(new Pos(mr, mc));
                }
            }
        }
    }
}
