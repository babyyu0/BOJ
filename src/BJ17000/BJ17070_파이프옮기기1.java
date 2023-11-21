package BJ17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17070_파이프옮기기1 {
    private static class Pos {
        private Pos(int r, int c, char dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        int r, c;
        char dir;  // 'R' 'D' 'C'

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }
    }

    private static int N;
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 격자 크기
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = (st.nextToken().equals("1"));
            }
        }

        findRoad();
    }

    private static void findRoad() {
        int[][] dp = new int[N][N];
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(0, 1, 'R'));

        Pos pos;
        int dr, dc;
        while (!queue.isEmpty()) {
            // System.out.println(queue);
            pos = queue.poll();
            dp[pos.r][pos.c]++;

            dr = pos.r + 1;
            dc = pos.c + 1;
            if (pos.dir != 'D' && dc < N && !map[pos.r][dc]) {  // 가로 방향
                queue.add(new Pos(pos.r, dc, 'R'));
            }
            if (pos.dir != 'R' && dr < N && !map[dr][pos.c]) {
                queue.add(new Pos(dr, pos.c, 'D'));
            }
            if (dr < N && dc < N && !map[dr][pos.c] && !map[pos.r][dc] && !map[dr][dc]) {
                queue.add(new Pos(dr, dc, 'C'));
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
