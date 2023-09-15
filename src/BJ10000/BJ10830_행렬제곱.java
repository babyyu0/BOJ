package BJ10000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ10830_행렬제곱 {

    private static int N;  // 행렬의 크기
    private static long B;  // 제곱수
    private static long[][] A;  // 행렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new long[N][N];  // 행렬 배열 생성

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] resultArr = findMod(A, B);  // 행렬 배열 생성

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(resultArr[r][c] % 1000).append(" ");
            }
            sb.append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    private static long[][] findMod(long[][] arr, long cnt) {
        if(cnt == 1) return arr;

        long[][] tmp = findMod(arr, cnt / 2);
        tmp = multiply(tmp, tmp);
        if(cnt % 2 != 0) {
            return multiply(tmp, arr);
        }
        return tmp;
    }

    private static long[][] multiply(long[][] firstArr, long[][] secondArr) {
        long[][] resultArr = new long[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int i = 0; i < N; i++) {
                    resultArr[r][c] += (firstArr[r][i] * secondArr[i][c]);
                }
                resultArr[r][c] %= 1000;
            }
        }

        return resultArr;
    }
}
