package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ02573_빙산 {
    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    private static int N, M;
    private static int[][] map;
    private static List<Pos> posList;
    private static int posCount;
    private static Queue<Pos> queue;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(0 < map[i][j]) {
                    posCount++;
                }
            }
        }
        int day = 0;

        if(posCount <= 1) {
            System.out.println(day);
            return;
        }

        boolean flag = false;
        while(!flag) {
            day++;
            flag = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(0 < map[i][j]) {
                        flag = false;
                        if(bfs(new Pos(i, j), new boolean[N][M], 0) != posCount) {
                            System.out.println(day - 1);
                            return;
                        }
                        break;
                    }
                }
                if(!flag) break;
            }
            if(posCount <= 1) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(0);
    }

    private static int bfs(Pos pos, boolean[][] visited, int count) {
        queue.add(pos);
        visited[pos.r][pos.c] = true;

        int nr, nc, zero;
        while(!queue.isEmpty()) {
            //System.out.println(queue);
            pos = queue.poll();
            count++;
            zero = 0;
            for (int i = 0; i < 4; i++) {
                nr = pos.r + dr[i];
                nc = pos.c + dc[i];
                if(0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {
                    if(map[nr][nc] == 0) zero++;
                    else if (0 < map[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.add(new Pos(nr, nc));
                    }
                }
            }
            map[pos.r][pos.c] = Math.max(0, map[pos.r][pos.c] - zero);
            if(map[pos.r][pos.c] == 0) {
                posCount--;
                count--;
            }
        }

        return count;
    }
    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
