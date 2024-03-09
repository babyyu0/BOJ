package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ02589_보물섬 {
    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }
    private static char[][] map;
    private static Queue<Pos> queue;
    private static int N, M, maxMove;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 'L') {
                    bfs(i, j, new int[N][M]);
                    //System.out.println("현재 MAX MOVE: " + maxMove + "\n");
                }
            }
        }

        System.out.println(maxMove - 1);
    }

    private static void bfs(int r, int c, int[][] visited) {
        queue.add(new Pos(r, c));
        visited[r][c] = 1;

        int move = 0; Pos pos; int nr, nc;
        while(!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                nr = pos.r + dr[i];
                nc = pos.c + dc[i];

                if(0 <= nr && nr < N && 0 <= nc && nc < M && visited[nr][nc] <= 0 && map[nr][nc] == 'L') {
                    //System.out.printf("(%2d, %2d)로 이동 : %2d\n", nr, nc, visited[pos.r][pos.c] + 1);
                    maxMove = Math.max((visited[nr][nc] = visited[pos.r][pos.c] + 1), maxMove);
                    queue.add(new Pos(nr, nc));
                }
            }
        }

        /*
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%2d ", visited[i][j]);
            }
            System.out.println();
        }
         */
    }
}
