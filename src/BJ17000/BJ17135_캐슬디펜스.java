package BJ17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17135_캐슬디펜스 {
    private static int N, M, D;  // 행의 수 N, 열의 수 M, 공격 거리 제한 D
    private static int[] COUNT;
    private static int[][] map;
    private static final int[] dc = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        COUNT = new int[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int MAX_SUM = 0;
        boolean[][][] visited;
        for (int i = 0; i < M; i++) {
            visited = catchEnemy(i, 0, new boolean[N][M][3]);
            System.out.println();
            for (int j = i + 1; j < M; j--) {
                visited = catchEnemy(i, 1, visited);
                for (int k = j + 1; k < M; k--) {
                    visited = catchEnemy(i, 2, visited);
                }
            }
        }

        System.out.println(MAX_SUM);
    }

    private static boolean[][][] catchEnemy(int archer, int num, boolean[][][] visited) {
        int nr, nc; boolean flag;
        for (int i = N - 1; 0 <= i; i--) {
            // 거리 계산
            System.out.printf("====== %d 열 시작 ======\n", i);
            for (int j = 0; j < D; j++) {
                if (i - j < 0) continue;
                for (int k = -D + j + 1; k < D - j; k++) {
                    if(archer + k < 0 || N <= archer + k)  continue;
                    nr = i - j;
                    nc = archer + k;
                    System.out.printf("(%2d, %2d) 탐색\n", nr, nc);

                    if(map[nr][nc] == 1 && !visited[nr][nc][num]) {
                        flag = false;
                        for (int l = 0; l < num; l++) {
                            if(visited[nr][nc][l]) {
                                flag = true;
                                break;
                            }
                        }

                        if(!flag) {  // 적을 잡을 수 있을 경우
                            visited[nr][nc][num] = true;
                            System.out.printf("(%2d, %2d)에 위치한 적을 %2d번 궁수가 잡다.\n", nr, nc, num + 1);
                            j = D;
                            break;
                        }
                    }
                }
            }
        }
        return visited;
    }
}
