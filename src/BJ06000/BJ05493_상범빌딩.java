package BJ06000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ05493_상범빌딩 {
    private static class Pos {
        public Pos(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }

        int l, r, c;
    }

    private static int L, R, C;
    private static Queue<Pos> queue;
    private static int[][][] map;

    private static final int[] dr = {-1, 1, 0, 0, 0, 0}, dc = {0, 0, -1, 1, 0, 0}, dl = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        queue = new ArrayDeque<>();
        map = new int[30][30][30];
        int[] end = new int[3];

        String s;
        while (L != 0) {
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        switch (s.charAt(k)) {
                            case 'S':
                                queue.add(new Pos(i, j, k));
                                map[i][j][k] = 0;
                                break;
                            case 'E':
                                end = new int[]{i, j, k};
                            case '.':
                                map[i][j][k] = L * R * C + 1;
                                break;
                            case '#':
                                map[i][j][k] = -1;
                                break;
                            default:
                                break;
                        }
                    }
                }
                br.readLine();
            }
            move();
            if (map[end[0]][end[1]][end[2]] == L * R * C + 1) {
                sb.append("Trapped!\n");
            } else {
                sb.append("Escaped in ").append(map[end[0]][end[1]][end[2]]).append(" minute(s).\n");
            }

            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void move() {
        Pos pos;
        int nl, nr, nc;
        while (!queue.isEmpty()) {
            pos = queue.poll();
            for (int i = 0; i < 6; i++) {
                nl = pos.l + dl[i];
                nr = pos.r + dr[i];
                nc = pos.c + dc[i];

                if (0 <= nl && nl < L && 0 <= nr && nr < R && 0 <= nc && nc < C && map[pos.l][pos.r][pos.c] + 1 < map[nl][nr][nc]) {
                    map[nl][nr][nc] = map[pos.l][pos.r][pos.c] + 1;
                    queue.add(new Pos(nl, nr, nc));
                }
            }
        }
    }
}
