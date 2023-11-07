package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ02638_치즈 {

    private static class Pos {
        private Pos(int r, int c) {
            this.r = r; this.c = c;
        }
        int r, c;
    }
    private static int N, M;
    private static boolean[][] map;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().equals("1");
            }
        }

        int count = 0;
        while(!bfs(new boolean[N][M])) {
            count++;
        }

        System.out.println(count);
    }

    private static boolean bfs(boolean[][] visited) {
        Queue<Pos> queue = new ArrayDeque<>();
        boolean flag = true;
        queue.add(new Pos(0, 0));

        Pos pos; int mr, mc;
        while(!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                mr = pos.r + dr[i]; mc = pos.c + dc[i];
                if(0 <= mr && mr < N && 0 <= mc && mc < M) {
                    if(map[mr][mc] && visited[mr][mc]) {  // 방문한 치즈일 경우
                        map[mr][mc] = false;
                        flag = false;
                    } else if(!visited[mr][mc]) {  // 방문하지 않았을 경우
                        visited[mr][mc] = true;
                        if(!map[mr][mc]) {  // 치즈 아닐 경우
                            queue.add(new Pos(mr, mc));  // 방문하기
                        }
                    }
                }
            }
        }

        return flag;
    }
}
