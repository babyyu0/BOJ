package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ14502_연구소 {

    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M, MIN, DIV;
    private static int[][] map;
    private static boolean[][] visited;
    private static List<Pos> virusList;
    private static Set<Pos> set;
    private static Queue<Pos> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        set = new HashSet<>();
        queue = new ArrayDeque<>();
        virusList = new ArrayList<>();
        DIV = 3;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        MIN = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Pos(i, j));
                } else if (map[i][j] == 1) {
                    DIV++;
                }
            }
        }

        Pos pos;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    set.clear();

                    set.add(pos = new Pos(i, j));
                    setWalls(1, i, j);
                    set.remove(pos);
                }
            }
        }

        System.out.println(MIN);
    }

    private static void setWalls(int count, int r, int c) {
        if (count == 3) {
            int num = (N * M) - (DIV + findSafeArea(virusList.size()));
            MIN = Math.max(MIN, num);
            return;

        }
        Pos pos = new Pos(r, c);
        while ((pos = findWall(pos.r, pos.c)) != null) {
            if (map[pos.r][pos.c] == 0) {
                set.add(pos);
                setWalls(count + 1, pos.r, pos.c);
                set.remove(pos);
            }
        }
    }

    private static Pos findWall(int r, int c) {
        if (c + 1 < M) {
            return new Pos(r, c + 1);
        } else if (r + 1 < N) {
            return new Pos(r + 1, 0);
        } else {
            return null;
        }
    }

    private static int findSafeArea(int count) {
        queue.clear();
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        for (Pos virus : virusList) {
            queue.add(new Pos(virus.r, virus.c));
        }

        Pos pos;
        int mr, mc;
        while (!queue.isEmpty()) {
            pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                mr = pos.r + dr[i];
                mc = pos.c + dc[i];
                if (0 <= mr && 0 <= mc && mr < N && mc < M
                        && !visited[mr][mc] && map[mr][mc] == 0 && !set.contains(new Pos(mr, mc))) {
                    visited[mr][mc] = true;
                    count++;
                    queue.add(new Pos(mr, mc));
                }
            }
        }

        return count;
    }

    private static class Pos {

        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Pos pos) {
                return this.r == pos.r && this.c == pos.c;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
