package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14890_경사로 {
    private static int N, L, LOAD;
    private static int[][] map;
    private static boolean[][] isStair;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isStair = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        horizonCheck();
        verticalCheck();

        System.out.println(LOAD);
    }

    private static void horizonCheck() {
        isStair = new boolean[N][N];
        int canGo;
        for (int i = 0; i < N; i++) {
            canGo = 1;
            for (int j = 1; j < N; j++) {
                if(map[i][j] == map[i][j - 1]) continue;  // 같은 층일 때
                if(1 < Math.abs(map[i][j] - map[i][j - 1])) {  // 층 길이가 안 맞을 경우
                    canGo = 0;
                } else if (map[i][j - 1] < map[i][j]) {  // 본인이 오르는 층일 때
                    for (int k = 1; k <= L; k++) {  // 계단 세우기
                        if(j - k < 0 || isStair[i][j - k] || map[i][j - 1] != map[i][j - k]) {  // 계단이 벽에 막히거나, 평평하지 않거나, 이미 있을 경우
                            canGo = 0;
                            break;
                        }
                        isStair[i][j - k] = true;
                    }
                } else {  // 내려가는 층일 때
                    for (int k = 0; k < L; k++) {  // 계단 세우기
                        if(N <= j + k || isStair[i][j + k] || map[i][j] != map[i][j + k]) {  // 계단이 벽에 막히거나, 평평하지 않거나, 이미 있을 경우
                            canGo = 0;
                            break;
                        }
                        isStair[i][j + k] = true;
                    }
                }

                if(canGo == 0) break;
            }
            LOAD += canGo;
        }
    }

    private static void verticalCheck() {
        isStair = new boolean[N][N];
        int canGo;
        for (int i = 0; i < N; i++) {
            canGo = 1;
            for (int j = 1; j < N; j++) {
                if(map[j][i] == map[j - 1][i]) continue;  // 같은 층일 때
                if(1 < Math.abs(map[j][i] - map[j - 1][i])) {  // 층 길이가 안 맞을 경우
                    canGo = 0;
                } else if (map[j - 1][i] < map[j][i]) {  // 본인이 오르는 층일 때
                    for (int k = 1; k <= L; k++) {  // 계단 세우기
                        if(j - k < 0 || isStair[j - k][i] || map[j - 1][i] != map[j - k][i]) {  // 계단이 벽에 막히거나, 평평하지 않거나, 이미 있을 경우
                            canGo = 0;
                            break;
                        }
                        isStair[j - k][i] = true;
                    }
                } else {  // 내려가는 층일 때
                    for (int k = 0; k < L; k++) {  // 계단 세우기
                        if(N <= j + k || isStair[j + k][i] || map[j][i] != map[j + k][i]) {  // 계단이 벽에 막히거나, 평평하지 않거나, 이미 있을 경우
                            canGo = 0;
                            break;
                        }
                        isStair[j + k][i] = true;
                    }
                }

                if(canGo == 0) break;
            }
            LOAD += canGo;
        }
    }
}
