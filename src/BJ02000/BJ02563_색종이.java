package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02563_색종이 {
    private static final int MAP_SIZE = 100, PAPER_SIZE = 10;  // 색종이의 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 색종이의 개수
        int[][] map = new int[MAP_SIZE + 1][MAP_SIZE + 1];

        int width = 0;

        StringTokenizer st;
        int r, c;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            for (int j = r; j < r + PAPER_SIZE; j++) {
                for (int k = c; k < c + PAPER_SIZE; k++) {
                    map[j][k]++;
                    if (map[j][k] == 1) width++;
                }
            }
        }

        System.out.println(width);
    }
}
