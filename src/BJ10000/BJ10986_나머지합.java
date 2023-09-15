package BJ10000;

import java.io.*;
import java.util.StringTokenizer;

/**
 * TEST CASE :
10 4
4 2 8 7 1 5 9 2 4 3
 */

public class BJ10986_나머지합 {
    private static int N, M;
    private static long CNT;
    private static long[] numList, MOD;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numList = new long[N + 1];
        MOD = new long[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            MOD[i] = numList[i - 1] + Integer.parseInt(st.nextToken());
            if(numList[i] % M == 0) CNT++;
            MOD[(int) (numList[i] % M)]++;
        }

        //CNT += MOD[0];
        for(int i = 0; i < M; i++) {
            CNT += (MOD[i] * (MOD[i] - 1) / 2);
        }

        sb.append(CNT);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
