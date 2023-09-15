package BJ02000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ02630_색종이만들기 {

    private static int N;  // 한 변의 길이
    private static int[] paper;  // 흰색 종이, 파란색 종이
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        paper = new int[2];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        divide(0, 0, N);

        sb.append(paper[0]).append("\n").append(paper[1]);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void divide(int row, int col, int size) {
        // map[0][0]
        int pColor = map[row][col];

        // System.out.println(row + "부터 " + (row+size) + "까지, ");
        // System.out.println("\t" + col + "부터 " + (col+size) + "까지, ");
        for (int r = row; r < row + size; r++) {
            for (int c = col; c < col + size; c++) {
                if(map[r][c] != pColor) {
                    divide(row, col, size / 2);
                    divide(row + size / 2, col, size / 2);
                    divide(row, col + size / 2, size / 2);
                    divide(row + size / 2, col + size / 2, size / 2);

                    return;
                }
            }
        }
        paper[pColor]++;
    }
}
