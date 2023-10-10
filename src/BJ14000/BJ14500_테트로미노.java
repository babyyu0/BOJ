package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14500_테트로미노 {
    private static int N, M, sum, max;
    private static int[][] field;
    private static boolean[][] visited;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        field = new int[N][M];
        visited = new boolean[N][M];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sum = 0;
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, field[i][j]);
                visited[i][j] = false;

            }
        }

        System.out.println(max);
    }

    private static void dfs(int r, int c, int count, int sum) {
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc]) {  // 방문 가능할 경우
                visited[nr][nc] = true;
                if(count == 2) {
                    dfs(r, c, count + 1, sum + field[nr][nc]);
                }
                dfs(nr, nc, count + 1, sum + field[nr][nc]);
                visited[nr][nc] = false;
            }
        }
    }
}
