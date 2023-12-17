package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02567_색종이2 {
    private static final int MAP_SIZE = 100, PAPER_SIZE = 10;
    private static int N;  // 색종이의 수
    private static boolean[][] map, checkMap;  // 도화지 종이: T / 종이 X: F
    private static final int[] BR = {-1, 1, 0, 0}, BC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new boolean[MAP_SIZE + 2][MAP_SIZE + 2];
        checkMap = new boolean[MAP_SIZE + 2][MAP_SIZE + 2];

        N = Integer.parseInt(br.readLine());  // 색종이의 수 입력 받기

        int c, r;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken()) + 1;
            r = Integer.parseInt(st.nextToken()) + 1;

            for (int j = r; j < r + PAPER_SIZE; j++) {
                for (int k = c; k < c + PAPER_SIZE; k++) {
                    map[j][k] = true;
                }
            }
        }

        // printMap();
        calSize();
        // printCheckMap();
    }

    private static void calSize() {
        int count = 0; int mr, mc;
        for (int i = 1; i <= MAP_SIZE; i++) {
            for (int j = 1; j <= MAP_SIZE; j++) {
                for (int k = 0; k < 4; k++) {
                    mr = i + BR[k];
                    mc = j + BC[k];
                    if(map[i][j] && !map[mr][mc]) {
                        count++;
                        checkMap[i][j] = true;
                        // break;
                    }
                }
            }
        }
        System.out.println(count);
    }

    private static void printMap() {
        for (int i = 0; i <= MAP_SIZE; i++) {
            System.out.print((i < 10 ? "0" + i : i) + "  ");
            for (int j = 0; j <= MAP_SIZE; j++) {
                System.out.print((map[i][j] ? "█" : "▓"));
            }
            System.out.println();
        }
    }

    private static void printCheckMap() {
        for (int i = 0; i <= MAP_SIZE; i++) {
            System.out.print((i < 10 ? "0" + i : i) + "  ");
            for (int j = 0; j <= MAP_SIZE; j++) {
                System.out.print((checkMap[i][j] ? "█" : "▓"));
            }
            System.out.println();
        }
    }
}
