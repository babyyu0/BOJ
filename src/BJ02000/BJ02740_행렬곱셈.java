package BJ02000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ02740_행렬곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 행 크기
        int M = Integer.parseInt(st.nextToken());  // 열 크기

        int[][] firstArr = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                firstArr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  // 행 크기
        int K = Integer.parseInt(st.nextToken());  // 열 크기
        int[][] secondArr = new int[M][K];

        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < K; c++) {
                secondArr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] resultArr = new int[N][K];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < K; c++) {
                for (int i = 0; i < M; i++) {
                    // System.out.println("firstArr[" + r + "][" + i + "] * secondArr[" + i + "][" + c + "]");
                    resultArr[r][c] += (firstArr[r][i] * secondArr[i][c]);
                }
                sb.append(resultArr[r][c]).append(" ");
            }
            sb.append("\n");
        }

        
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
