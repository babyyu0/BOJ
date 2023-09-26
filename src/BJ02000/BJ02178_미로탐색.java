package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ02178_미로탐색 {
    private static class Pos {
        Pos(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }

        int r, c, count;
    }

    private static int N, M;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];

        char[] chars;
        for (int i = 0; i < N; i++) {
            chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                visited[i][j] = (chars[j] == '0');
            }
        }

        System.out.println(bfs(new ArrayDeque<>()));
    }

    private static int bfs(Queue<Pos> queue) {
        visited[0][0] = true;
        queue.add(new Pos(0, 0, 1));
        Pos curPos;
        while (!queue.isEmpty()) {
            curPos = queue.poll();
            for (int i = 0; i < 4; i++) {
                if((curPos.r + dr[i]) == (N - 1) && (curPos.c + dc[i]) == (M - 1)) return curPos.count + 1;
                if (0 <= (curPos.r + dr[i]) && (curPos.r + dr[i]) < N
                        && 0 <= (curPos.c + dc[i]) && (curPos.c + dc[i]) < M
                        && !visited[curPos.r + dr[i]][curPos.c + dc[i]]) {
                    visited[curPos.r + dr[i]][curPos.c + dc[i]] = true;
                    queue.add(new Pos(curPos.r + dr[i], curPos.c + dc[i], curPos.count + 1));
                }
            }
        }

        return -1;
    }

}
