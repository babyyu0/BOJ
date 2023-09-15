package BJ09000;

import java.io.*;
import java.util.*;

public class BJ09663_NQUEEN {

    private static int N;  // 퀸 개수
    private static int[] queen;  // 퀸의 열 위치
    private static int numCnt;

    public static void main(String[] args) throws IOException {  // Start Main
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // Opened BufferedReader
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));  // Opened BufferedWriter
        StringBuilder sb = new StringBuilder();  // Opened StringBuilder
        StringTokenizer st;  // Default StringTokenizer

        N = Integer.parseInt(br.readLine());
        queen = new int[N];

        dfs(0);

        sb.append(numCnt);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int cnt) {
        if(cnt == N) {
            numCnt++;
            return;
        }

        for(int i = 0; i < N; i++) {
            if(isPossible(i, cnt)) {
                queen[cnt] = i;
                dfs(cnt + 1);
            }
        }
    }

    private static boolean isPossible(int col, int cnt) {
        for (int i = 0; i < cnt; i++) {
            if(col  == queen[i] || col == (queen[i] - (cnt - i)) || col == (queen[i] + (cnt - i))) return false;
        }
        return true;
    }
}