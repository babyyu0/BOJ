package BJ02000;

import java.io.*;
import java.util.*;

public class BJ02583_영역구하기 {

    private static class Pos {
        public Pos(int c, int r) {
            this.c = c;
            this.r = r;
        }

        int r, c;
    }

    private static int M, N, K;
    private static boolean[][] map;  // 방문 처리할 변수
    private static Queue<Pos> queue;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new boolean[M][N];
        queue = new ArrayDeque<>();

        Pos[] pos = new Pos[2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            pos[0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            pos[1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for (int j = pos[0].r; j < pos[1].r; j++) {
                for (int k = pos[0].c; k < pos[1].c; k++) {
                    map[j][k] = true;
                }
            }
        }
        //print();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(!map[i][j]) {
                    map[i][j] = true;
                    list.add(findBox(new Pos(j, i), 1));
                }
            }
        }
        Collections.sort(list);

        sb.append(list.size()).append("\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();


    }

    private static int findBox(Pos pos, int count) {
        queue.clear();
        queue.add(pos);

        int mr, mc;
        while(!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];

                if(0 <= mr && mr < M && 0 <= mc && mc < N && !map[mr][mc]) {
                    map[mr][mc] = true;
                    queue.add(new Pos(mc, mr));
                    count++;
                }
            }
        }

        return count;
    }

    private static void print() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] ? "■" : "□");
            }
            System.out.println();
        }
    }
}
