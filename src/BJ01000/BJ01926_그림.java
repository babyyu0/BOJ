package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ01926_그림 {
    private static int N, M, MAX_SIZE;
    private static boolean[][] map;
    private static Queue<int[]> queue;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queue = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().equals("0");
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!map[i][j]) {
                    count++;
                    map[i][j] = true;
                    //System.out.printf("(%d, %d) 너비 더하기\n", i, j);
                    queue.add(new int[]{i, j});
                    countSize(1);
                }
            }
        }

        System.out.println(count);
        System.out.println(MAX_SIZE);
    }

    private static void countSize(int size) {
        int[] pos; int nr, nc;
        while(!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                nr = pos[0] + dr[i];
                nc = pos[1] + dc[i];
                if(0 <= nr && nr < N && 0 <= nc && nc < M && !map[nr][nc]) {
                    map[nr][nc] = true;
                    //System.out.printf("(%d, %d) 너비 더하기\n", nr, nc);
                    size++;
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        MAX_SIZE = Math.max(MAX_SIZE, size);
    }
}
