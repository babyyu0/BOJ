package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ01600_말이되고픈원숭이 {
    private static class Pos {
        public Pos(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }

        int r, c, k;
    }

    private static int K, W, H;  // 나이트식 이동 제한 횟수, 가로 세로 길이
    private static long[][][] map;
    private static final int INF = 20001;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};  // 원숭이 이동 경로
    private static final int[] kr = {-2, 2, -2, 2, -1, 1, -1, 1}, kc = {-1, 1, 1, -1, -2, 2, 2, -2};  // 말 이동 경로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new long[H][W][K + 1];  // 세로, 가로, 말 이동 횟수 3자원배열 생성

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                Arrays.fill(map[i][j], st.nextToken().equals("1") ? -1 : INF);  // 값을 전부 inf 아니면 장애물로 채우기
            }
        }
        Arrays.fill(map[0][0], 0);  // 시작점은 항상 0

        moveMap();  // 이동 위치 찾는 함수 호출

        long minMove = map[H - 1][W - 1][0];  // 최소 이동 횟수 선언 및 정의
        for (int i = 1; i <= K; i++) {
            minMove = Math.min(minMove, map[H - 1][W - 1][i]);  // 오른쪽 끝까지의 최소 이동 횟수 계산
        }

        System.out.println(minMove == INF ? -1 : minMove);  // 최소 이동 횟수 출력 (이동 못할 시 -1)

    }

    private static void moveMap() {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(0, 0, 0));  // 큐에 첫 이동점 삽입

        Pos pos;
        int nr, nc;
        while (!queue.isEmpty()) {  // BFS
            pos = queue.poll();
            for (int i = 0; i < 4; i++) {  // 원숭이용 이동
                nr = pos.r + dr[i];
                nc = pos.c + dc[i];  // 이동 위치 계산

                if (0 <= nr && nr < H && 0 <= nc && nc < W && map[pos.r][pos.c][pos.k] + 1 < map[nr][nc][pos.k]) {  // 이동 가능할 경우 (이동 횟수보다 작은 경우)
                    map[nr][nc][pos.k] = map[pos.r][pos.c][pos.k] + 1;  // 최소 이동 횟수 갱신
                    queue.add(new Pos(nr, nc, pos.k));  // Queue에 삽입 (새로운 인접 이동거리 계산을 위해)
                }
            }

            for (int i = 0; i < 8 && pos.k < K; i++) {  // 나이트용 이동
                nr = pos.r + kr[i];
                nc = pos.c + kc[i];  // 이동 위치 계산

                if (0 <= nr && nr < H && 0 <= nc && nc < W && map[pos.r][pos.c][pos.k] + 1 < map[nr][nc][pos.k + 1]) {  // 이동 가능할 경우 (이동 횟수보다 작은 경우)
                    map[nr][nc][pos.k + 1] = map[pos.r][pos.c][pos.k] + 1;  // 최소 이동 횟수 갱신
                    queue.add(new Pos(nr, nc, pos.k + 1));  // Queue에 삽입 (새로운 인접 이동거리 계산을 위해)
                }
            }
        }
    }

    private static void printMap(int k) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.printf("%3d ", map[i][j][k]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
