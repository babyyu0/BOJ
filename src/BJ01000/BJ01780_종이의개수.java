package BJ01000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ01780_종이의개수 {

    private static int N, pColor;  // 한 변의 길이
    private static int[] paper;  // -1, 0, 1 종이
    private static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        paper = new int[3];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        sb.append(paper[0]).append("\n").append(paper[1]).append("\n").append(paper[2]);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }


    private static void divide(int row, int col, int size) {
        // map[0][0]
        pColor = map[row][col];
        for (int r = row; r < row + size; r++) {
            for (int c = col; c < col + size; c++) {
                if(map[r][c] != pColor) {
                    divide(row, col, size / 3);
                    divide(row + size / 3, col, size / 3);
                    divide(row + size / 3 + size / 3, col, size / 3);
                    divide(row + size / 3 + size / 3, col + size / 3, size / 3);
                    divide(row, col + size / 3, size / 3);
                    divide(row, col + size / 3 + size / 3, size / 3);
                    divide(row + size / 3, col + size / 3 + size / 3, size / 3);
                    divide(row + size / 3, col + size / 3, size / 3);
                    divide(row + size / 3 + size / 3, col + size / 3 + size / 3, size / 3);

                    return;
                }
            }
        }
        paper[pColor + 1]++;
    }
}
