package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 시작점과 끝점은 항상 0
public class BJ02206_벽부수고이동하기 {

    private static class Road implements Comparable<Road> {

        private Road(int r, int c, int depth, int crashed) {
            this.r = r;
            this.c = c;  // 현재 위치한 열과 행
            this.depth = depth;  //
            this.crashed = crashed;
        }

        int r, c, depth, crashed;

        @Override
        public String toString() {
            return "Road{" +
                    "r=" + r +
                    ", c=" + c +
                    ", depth=" + depth +
                    ", crashed=" + crashed +
                    '}';
        }

        @Override
        public int compareTo(Road r) {
            if (this.depth < r.depth) {
                return -1;
            } else if (r.depth < this.depth) {
                return 1;
            } else if (this.r < r.r) {
                return 1;
            } else if (r.r < this.r) {
                return -1;
            } else if (this.c < r.c) {
                return 1;
            } else if (r.c < this.c) {
                return -1;
            } else if (this.crashed < r.crashed) {
                return -1;
            } else if (r.crashed < this.crashed) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static int N, M; // 열, 행
    private static int[][] roads;
    private static Road[][] move;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 열 받기
        M = Integer.parseInt(st.nextToken());  // 행 받기

        roads = new int[N][M];  // 벽 있는지 저장할 맵 만들기
        move = new Road[N][M];  // 이동 저장할 맵 만들기

        String road;
        for (int r = 0; r < N; r++) {
            road = br.readLine();
            for (int c = 0; c < M; c++) {
                roads[r][c] = road.charAt(c) - '0';  // 벽이면 true, 아니면 false
            }
        }

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        move[0][0] = new Road(0, 0, 1, 0);
        System.out.println(findShortest2());
    }

    private static int findShortest2() {
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.add(move[0][0]);

        Road curRoad;
        int mr, mc;
        while (!queue.isEmpty()) {
            // System.out.println(queue);
            curRoad = queue.poll();

            for (int i = 0; i < 4; i++) {
                mr = curRoad.r + dr[i];
                mc = curRoad.c + dc[i];
                if (mr < 0 || N <= mr || mc < 0 || M <= mc) {  // 배열을 벗어난 경우 continue
                    continue;
                } else if (1 < curRoad.crashed + roads[mr][mc]) {  // 벽에 두번째 막힌 경우 continue
                    continue;
                } else if (move[mr][mc] != null && move[mr][mc].depth <= curRoad.depth + 1
                        && move[mr][mc].crashed <= curRoad.crashed
                        + roads[mr][mc]) {  // 현재 이동하려는 경로보다 depth가 작을 경우 continue
                    continue;
                }

                move[mr][mc] = new Road(mr, mc, curRoad.depth + 1, curRoad.crashed + roads[mr][mc]);
                if (mr == N - 1 && mc == M - 1) {  // 목적지일 경우
                    return move[mr][mc].depth;
                }
                queue.add(move[mr][mc]);
            }
        }
        return -1;
    }
}
