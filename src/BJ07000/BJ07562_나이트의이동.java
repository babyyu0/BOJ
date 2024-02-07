package BJ07000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ07562_나이트의이동 {
    private static class Pos {
        public Pos(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }

        int r, c, move;
    }

    private static Queue<Pos> queue;
    private static final int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2}, dc = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        queue = new ArrayDeque<>();

        int l;
        Pos knight, loc;
        for (int i = 0; i < tc; i++) {
            l = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            knight = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            st = new StringTokenizer(br.readLine());
            loc = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

            sb.append(bfs(l, new int[l][l], knight, loc)).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long bfs(int l, int[][] visited, Pos knight, Pos loc) {
        for (int i = 0; i < l; i++) {
            Arrays.fill(visited[i], 300);
        }

        queue.add(knight);
        visited[knight.r][knight.c] = 0;

        Pos pos;
        int mr, mc;
        while (!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 8; i++) {
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];

                if (0 <= mr && mr < l && 0 <= mc && mc < l && pos.move + 1 < visited[mr][mc]) {
                    visited[mr][mc] = pos.move + 1;
                    queue.add(new Pos(mr, mc, pos.move + 1));
                }
            }
        }

        //System.out.println(Arrays.deepToString(visited).replace("],", "]\n"));
        return visited[loc.r][loc.c];
    }
}
