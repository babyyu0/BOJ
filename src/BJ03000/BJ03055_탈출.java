package BJ03000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ03055_탈출 {
    private static class Hedgehog {
        public Hedgehog(int r, int c, int move) {
            this.r = r;
            this.c = c;
            this.move = move;
        }

        int r, c, move;
    }

    private static int R, C;
    private static List<char[][]> map;
    private static boolean[][] visited;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new ArrayList<>();
        map.add(new char[R][C]);
        visited = new boolean[R][C];
        Hedgehog hedgehog = null;

        String str;
        for (int i = 0; i < R; i++) {
            str = br.readLine();
            for (int j = 0; j < C; j++) {
                map.get(0)[i][j] = str.charAt(j);
                if (map.get(0)[i][j] == 'S') {  // 고슴도치 위치
                    hedgehog = new Hedgehog(i, j, 0);
                    visited[i][j] = true;
                    map.get(0)[i][j] = '.';
                }
            }
        }

        findBeaver(hedgehog);
        System.out.println("KAKTUS");
    }

    private static void findBeaver(Hedgehog hedgehog) {
        Queue<Hedgehog> queue = new ArrayDeque<>();
        queue.add(hedgehog);

        int nr, nc;
        while (!queue.isEmpty()) {
            hedgehog = queue.poll();
            if (map.size() <= hedgehog.move + 1)
                fillWater(hedgehog.move + 1);  // 다음 칸 물 채우기
            //printMap(map.get(hedgehog.move), hedgehog);

            for (int i = 0; i < 4; i++) {
                nr = hedgehog.r + dr[i];
                nc = hedgehog.c + dc[i];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc]) {
                    if (map.get(hedgehog.move)[nr][nc] == '.' && map.get(hedgehog.move + 1)[nr][nc] == '.') {
                        queue.add(new Hedgehog(nr, nc, hedgehog.move + 1));
                        visited[nr][nc] = true;
                    } else if (map.get(hedgehog.move)[nr][nc] == 'D') {
                        System.out.println(hedgehog.move + 1);
                        System.exit(0);
                    }
                }
            }
        }
    }

    private static void fillWater(int move) {
        map.add(new char[R][C]);
        int nr, nc;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map.get(move - 1)[i][j] == '*') {
                    for (int k = 0; k < 4; k++) {
                        nr = i + dr[k];
                        nc = j + dc[k];
                        if (0 <= nr && nr < R && 0 <= nc && nc < C && map.get(move - 1)[nr][nc] == '.') {
                            map.get(move)[nr][nc] = '*';
                        }
                    }
                }
                if (map.get(move)[i][j] == '\0') map.get(move)[i][j] = map.get(move - 1)[i][j];
            }
        }
    }

    private static void printMap(char[][] map, Hedgehog hedgehog) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (i == hedgehog.r && j == hedgehog.c) {
                    System.out.print("S ");
                } else {
                    System.out.printf("%c ", map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
