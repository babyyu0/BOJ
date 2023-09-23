package BJ07000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ07576_토마토 {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M, count;
    private static int[][] map;
    private static List<Pos> list;
    private static Queue<Pos> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        queue = new ArrayDeque<>();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int tmp;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) list.add(new Pos(i, j));
                if (tmp != 0) {
                    map[i][j] = 1;
                    count++;
                }

            }
        }

        int day = bfs(false);

        System.out.println(((count == (N * M)) ? day : 0) - 1);
    }

    private static int bfs(boolean tmpCnt) {
        for (int i = 0; i < list.size(); i++) {
            queue.add(list.get(i));
        }

        Pos curPos = null;
        while (!queue.isEmpty()) {
            curPos = queue.poll();
            // System.out.println("[" + curPos.r + ", " + curPos.c + "] 방문!");

            for (int i = 0; i < 4; i++) {
                try {
                    if (map[curPos.r + dr[i]][curPos.c + dc[i]] == 0) {
                        map[curPos.r + dr[i]][curPos.c + dc[i]] = map[curPos.r][curPos.c] + 1;
                        queue.add(new Pos(curPos.r + dr[i], curPos.c + dc[i]));
                        count++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
        // System.out.println("방문 정보 : " + count);

        try {
            return map[curPos.r][curPos.c];
        } catch(NullPointerException e) {
            return -1;
        }
    }

    private static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
