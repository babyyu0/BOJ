package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ02468_안전영역 {
    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    private static int N;  // 행렬 크기  2-100
    private static int[][] map; // 1-100
    private static int[] count;
    private static boolean[][][] visited;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int maxHeight = 0;  // 최대 높이를 담을 변수
        StringTokenizer st; // 문자열을 토큰으로 자를 변수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());  // 받은 문자열 토큰으로 자르기
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());  // 각 숫자 삽입
                maxHeight = Math.max(maxHeight, map[i][j]);  // 최대 높이 담기
            }
        }
        visited = new boolean[N][N][maxHeight + 1];
        count = new int[maxHeight + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 1; k <= maxHeight; k++) {
                    if(k <= map[i][j] && !visited[i][j][k]) {
                        count[k]++;
                        visited[i][j][k] = true;
                        //System.out.println(k + " 높이부터 안 잠겼을 때 " + new Pos(i, j) + "에서 시작...");
                        dfs(new Pos(i, j), k);
                    }
                }
            }
        }

        Arrays.sort(count);
        //System.out.println(Arrays.toString(count));
        System.out.println(count[maxHeight]);
    }

    private static void dfs(Pos pos, int h) {
        int mr, mc;
        for (int i = 0; i < 4; i++) {
            mr = pos.r + dr[i];
            mc = pos.c + dc[i];

            if(0 <= mr && mr < N && 0 <= mc && mc < N && h <= map[mr][mc] && !visited[mr][mc][h]) {
                visited[mr][mc][h] = true;
                dfs(new Pos(mr, mc), h);
            }
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((visited[i][j][5]) ? "■" : "□");
            }
            System.out.println();
        }
    }
}
