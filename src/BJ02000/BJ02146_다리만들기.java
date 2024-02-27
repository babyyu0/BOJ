package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ02146_다리만들기 {
    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }

    private static class Sea {
        public Sea(int land, int move) {
            this.land = land;
            this.move = move;
        }

        int land, move;
    }

    private static int N, MIN_BRIDGE; // 지도의 크기
    private static int[][] map; // 지도
    private static Sea[][] dp;
    private static Queue<Pos> queue;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        /* 초기 설정 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        queue = new ArrayDeque<>();

        N = Integer.parseInt(br.readLine());  // 지도 크기 입력 받기
        map = new int[N][N];  // 지도 크기 초기화
        dp = new Sea[N][N];
        MIN_BRIDGE = 100 * 100;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findLand();  // 섬 구분하기
        findClosedSea();  // 설치 가능 다리 구분하기
        System.out.println(MIN_BRIDGE);
    }

    private static void findLand() {
        int checkNum = 2;  // 2부터 구분 (1은 섬 자체를 체크하는 정수이기 때문)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {  // 구분되지 않은 섬일 경우
                    map[i][j] = checkNum;  // 해당 섬의 구분 index는 checkNum
                    checkLand(i, j, checkNum++);  // BFS로 해당 섬을 구분하는 함수 호출
                }
            }
        }
    }

    private static void checkLand(int r, int c, int checkNum) {
        Pos pos;
        int mr, mc;
        queue.add(new Pos(r, c));
        while (!queue.isEmpty()) {  // BFS 순환
            pos = queue.poll();
            for (int i = 0; i < 4; i++) {  // 사방 탐색
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];
                if (0 <= mr && mr < N && 0 <= mc && mc < N && map[mr][mc] == 1) {  // 연결되는 + 구분되지 않은 섬일 경우
                    map[mr][mc] = checkNum;  // checkNum index로 구분
                    queue.add(new Pos(mr, mc));  // 해당 위치의 주변 탐색하기 위해 queue에 삽입
                }
            }
        }
    }

    private static void findClosedSea() {
        int mr, mc;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {  // 완전 탐색
                if (map[i][j] == 0) {  // 해당 위치가 바다일 경우
                    for (int k = 0; k < 4; k++) {  // 사방 탐색
                        mr = i + dr[k];
                        mc = j + dc[k];
                        if (0 <= mr && mr < N && 0 <= mc && mc < N && 1 < map[mr][mc]) {  // 섬과 인접해있는 바다일 경우
                            dp[i][j] = new Sea(map[mr][mc], 1);  // 설치 가능 다리 길이 (1) 초기화
                            checkSea(i, j, map[mr][mc]);  // BFS로 해당 위치에서부터 설치 가능 다리 길이 탐색할 함수 호출
                        }
                    }
                }
            }
        }
    }

    private static void checkSea(int r, int c, int land) {
        queue.add(new Pos(r, c));
        Pos pos;
        int mr, mc;
        while (!queue.isEmpty()) {  // BFS 순환
            pos = queue.poll();

            for (int i = 0; i < 4; i++) {  // 사방 탐색
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];

                if (0 <= mr && mr < N && 0 <= mc && mc < N && dp[pos.r][pos.c].move < MIN_BRIDGE) {  // 현재 다리 길이가 최소 다리 길이보다 짧을 경우 + 이동 가능할 경우
                    if (0 < map[mr][mc]) {  // 섬에 도달했을 경우
                        if (map[mr][mc] != land) {  // 도달한 섬이 출발 섬과 다른 섬일 경우
                            MIN_BRIDGE = Math.min(MIN_BRIDGE, dp[pos.r][pos.c].move);  // 최단 거리 초기화
                        }
                    } else if (dp[mr][mc] != null && dp[mr][mc].land != land) { // 이미 잰 거리가 존재 + 다른 섬에서 잰 거리일 경우
                        MIN_BRIDGE = Math.min(MIN_BRIDGE, dp[pos.r][pos.c].move + dp[mr][mc].move);  // 최단 거리 초기화
                    } else if (dp[mr][mc] == null && dp[pos.r][pos.c].move + 1 < MIN_BRIDGE) {  // 잰 거리가 없음 + 최소 다리 길이보다 짧을 경우
                        dp[mr][mc] = new Sea(land, dp[pos.r][pos.c].move + 1);  // 설치 가능 다리 길이 초기화
                        queue.add(new Pos(mr, mc));  // 해당 위치의 주변 탐색하기 위해 큐에 삽입 
                    } else if (dp[mr][mc] != null && dp[pos.r][pos.c].move + 1 < dp[mr][mc].move) {  // 이미 잰 거리가 존재 + 출발 섬에서 잰 거리지만, 더 짧게 다리를 세울 수 있을 경우
                        dp[mr][mc] = new Sea(land, dp[pos.r][pos.c].move + 1);  // 더 짧은 거리의 다리로 초기화
                        queue.add(new Pos(mr, mc));  // 해당 위치의 주변 탐색하기 위해 큐에 삽입
                    }
                }
            }
        }
    }
}

/*
public class BJ02146_다리만들기 {
    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }
    private static int N, MIN_BRIDGE; // 지도의 크기
    private static int[][] map; // 지도
    private static int[][][] dp;
    private static Queue<Pos> queue;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        queue = new ArrayDeque<>();
        MIN_BRIDGE = 100 * 100;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt("-" + st.nextToken());
            }
        }

        findLand();  // 육지 구분하기
        //printMap();
        selectBridge();  // 섬을 연결하는 짧은 다리 찾기
        //printDp(3);
        System.out.println(MIN_BRIDGE);
    }

    private static void findLand() {
        int checkNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == -1) {
                    map[i][j] = checkNum;
                    checkLand(i, j, checkNum++);
                }
            }
        }
        dp = new int[checkNum][N][N];
    }

    private static void checkLand(int r, int c, int checkNum) {
        Pos pos; int mr, mc;
        queue.add(new Pos(r, c));
        while (!queue.isEmpty()) {
            pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];
                if(0 <= mr && mr < N && 0 <= mc && mc < N && map[mr][mc] == -1) {
                    map[mr][mc] = checkNum;
                    queue.add(new Pos(mr, mc));
                }
            }
        }
    }

    private static void selectBridge() {
        int mr, mc;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0) {  // 바다일 경우
                    for (int k = 0; k < 4; k++) {
                        mr = i + dr[k];
                        mc = j + dc[k];
                        if(0 <= mr && mr < N && 0 <= mc && mc < N && 0 < map[mr][mc] && (dp[map[mr][mc]][i][j] == 0 || 1 < dp[map[mr][mc]][i][j])) {  // 육지와 인접해있을 경우
                            //System.out.println("좌표 (" + i + ", " + j + ") 바다는 육지와 인접해있습니다");
                            dp[map[mr][mc]][i][j] = 1;
                            checkBridge(i, j, map[mr][mc]);
                            break;
                        }
                    }
                }
            }
        }
    }
    private static void checkBridge(int r, int c, int checkNum) {
        Pos pos; int mr, mc;
        queue.add(new Pos(r, c));
        while (!queue.isEmpty()) {
            pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];
                if(0 <= mr && mr < N && 0 <= mc && mc < N) {  // 이동 가능할 때,
                    if(map[mr][mc] == 0 && (dp[checkNum][mr][mc] == 0 || dp[checkNum][pos.r][pos.c] + 1 < dp[checkNum][mr][mc])) {
                        dp[checkNum][mr][mc] = dp[checkNum][pos.r][pos.c] + 1;
                        queue.add(new Pos(mr, mc));
                    } else if(0 < map[mr][mc] && map[mr][mc] != checkNum) {
                        MIN_BRIDGE = Math.min(MIN_BRIDGE, dp[checkNum][pos.r][pos.c]);
                    }
                }
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%-2d", map[i][j]);
            }
            System.out.println();
        }
    }
    private static void printDp(int num) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%-2d", dp[num][i][j]);
            }
            System.out.println();
        }
    }
}
*/
