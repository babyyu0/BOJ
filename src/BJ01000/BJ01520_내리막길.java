package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01520_내리막길 {
    private static int M, N;  // 세로, 가로
    private static int[][] map;
    private static long[][] canVisit;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        //M = N = 500;  // 테스트 케이스
        map = new int[M][N];
        canVisit = new long[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(canVisit[i], -1L);
        }
        canVisit[0][0] = 1;

        //int k = 10000;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //map[i][j] = (k--);  // 테스트 케이스
            }
        }

        System.out.println(dp(M - 1, N - 1));

    }

    private static long dp(int r, int c) {  // (r, c)까지 도달할 수 있는 경우의 수 구하는 함수
        //if(r == 0 && c == 0) return 1;
        // 0 이상인 경우로 체크하는 이유는 0이라는 경우의 수도 구할 수 있기 때문
        if(0 <= canVisit[r][c]) return canVisit[r][c];  // 해당 위치의 경우의 수를 구했을 경우 그 경우의 수 반환

        int mr, mc;
        for (int i = 0; i < 4; i++) {  // 사방향 탐색
            mr = r + dr[i];
            mc = c + dc[i];

            if (0 <= mr && mr < M && 0 <= mc && mc < N && map[r][c] < map[mr][mc]) {  // (mr, mc) 방향에서 (r, c) 방향으로 이동할 수 있을 경우
                canVisit[r][c] += dp(mr, mc);  // (mr, mc) 방향의 경우의 수 더하기
            }
        }

        return ++canVisit[r][c];  // -1로 초기화 했기 때문에 + 1 해주기
    }

    private static void print(long[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.println(sb);
    }

}
