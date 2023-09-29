package BJ07000;

import java.io.*;
import java.util.*;

public class BJ07569_토마토 {
    private static class Pos {
        Pos(int h , int r, int c, int count) {
            this.h = h; this.r = r; this.c = c; this.count = count;
        }
        int h, r, c, count;

        @Override
        public String toString() {
            return "Pos{" +
                    "h=" + h +
                    ", r=" + r +
                    ", c=" + c +
                    ", count=" + count +
                    '}';
        }
    }
    private static int M, N, H;
    private static boolean[][][] map;
    private static Queue<Pos> queue;

    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new ArrayDeque<>();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H][N][M];

        int curInt, cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    curInt = Integer.parseInt(st.nextToken());
                    map[i][j][k] = (curInt != 0);
                    if(curInt == 1) queue.add(new Pos(i, j, k, 0));
                    else if(curInt == 0) cnt++;
                }
            }
        }

        if(cnt == 0) {  // 다 익었을 경우
            System.out.println(0); return;
        }

        System.out.println(bfs());
    }

    private static Pos curPos;
    private static int bfs() {
        while(!queue.isEmpty()) {
            // System.out.println("현재 큐 : " + queue);
            curPos = queue.poll();

            // 사방 탐색
            for (int i = 0; i < 4; i++) {
                if(0 <= (curPos.r + dr[i]) && 0 <= (curPos.c + dc[i])
                    && (curPos.r + dr[i]) < N && (curPos.c + dc[i]) < M
                    && !map[curPos.h][curPos.r + dr[i]][curPos.c + dc[i]]) {
                    map[curPos.h][curPos.r + dr[i]][curPos.c + dc[i]] = true;
                    queue.add(new Pos(curPos.h, curPos.r + dr[i], curPos.c + dc[i], curPos.count + 1));
                }
            }
            // 위 탐색
            if(0 <= (curPos.h - 1) && !map[curPos.h - 1][curPos.r][curPos.c]) {
                map[curPos.h - 1][curPos.r][curPos.c] = true;
                queue.add(new Pos(curPos.h - 1, curPos.r, curPos.c, curPos.count + 1));
            }
            // 아래 탐색
            if((curPos.h + 1) < H && !map[curPos.h + 1][curPos.r][curPos.c]) {
                map[curPos.h + 1][curPos.r][curPos.c] = true;
                queue.add(new Pos(curPos.h + 1, curPos.r, curPos.c, curPos.count + 1));
            }

        }

        if(isAllVisit()) return curPos.count;
        else return -1;
    }

    private static boolean isAllVisit() {
        for (int i = 0; i < H; i++) {
            // System.out.println(i + "번째 상자!");
            for (int j = 0; j < N; j++) {
                // System.out.println(Arrays.toString(map[i][j]));
                for (int k = 0; k < M; k++) {
                    if(!map[i][j][k]) return false;
                }
            }
        }
        return true;
    }
}
