package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01937_욕심쟁이판다 {
    private static int N;  // 숲 크기 (최대 500)
    private static long MAX_MOVE;
    private static long[][] map, dp; // 숲, 최대 먹을 수 있는 양 DP
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new long[N][N];
        dp = new long[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] == 0) {  // 해당 칸에 방문한 적이 없을 경우
                    MAX_MOVE = Math.max(MAX_MOVE, findMaxMove(i, j));  // 현재 칸의 이동 가능 값과 최대 이동 가능 값 비교
                }
            }
        }

        System.out.println(MAX_MOVE);
    }

    private static long findMaxMove(int r, int c) {
        if (1 <= dp[r][c]) return dp[r][c];  // 방문한 적이 있을 경우 해당 칸부터 갈 수 있는 최대 거리 반환
        dp[r][c]++;  // 방문한 적이 없을 경우 방문 처리 (+ 1)
        int nr, nc;
        for (int i = 0; i < 4; i++) {  // 사방 탐색
            nr = r + dr[i];
            nc = c + dc[i];

            if (canMove(nr) && canMove(nc) && map[r][c] < map[nr][nc]) {  // 이동할 수 있을 경우 (더 많은 대나무 존재)
                dp[r][c] = Math.max(1 + findMaxMove(nr, nc), dp[r][c]);  // 해당 방향으로 이동할 때의 최대 값이 다른 방향으로 이동했을 때의 최대값보다 큰지 비교
            }
        }

        return dp[r][c];  // 구한 값 반환
    }

    private static boolean canMove(int n) {  // ArrayOutOfIndex 방지
        return (0 <= n && n < N);
    }
}
