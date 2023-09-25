package BJ14000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14940_쉬운최단거리 {
    private static class Pos {
        Pos(int r, int c) { this.r = r; this.c = c; }
        int r, c;
    }
    private static int N, M;
    private static Pos start;
    private static int[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    start = new Pos(i, j);
                    map[i][j] = 0;
                } else if (map[i][j] == 1) {
                    map[i][j] = Integer.MAX_VALUE;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        //printMap("== 입력 후 ==");
        shortcut();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append((map[i][j] == Integer.MAX_VALUE)? -1 : map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static Queue<Pos> queue;
    private static void shortcut() {
        queue = new ArrayDeque<>();
        queue.add(start);

        Pos curPos;
        while(!queue.isEmpty()) {
            curPos = queue.poll();
            for(int i = 0; i < 4; i++) {
                try {
                    if (map[curPos.r][curPos.c] + 1 < map[curPos.r + dr[i]][curPos.c + dc[i]]) {
                        map[curPos.r + dr[i]][curPos.c + dc[i]] = map[curPos.r][curPos.c] + 1;
                        queue.add(new Pos(curPos.r + dr[i], curPos.c + dc[i]));
                    }
                } catch(IndexOutOfBoundsException e) { }
            }
            // printMap("== 방문 체크 ==");
        }
    }

    private static void printMap(String msg) {
        System.out.println(msg);
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}
