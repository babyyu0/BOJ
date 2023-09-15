package BJ02000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ02559_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());  // 입력 온도 개수
        int K = Integer.parseInt(st.nextToken());  // 연속 일수
        int MAX = Integer.MIN_VALUE;
        int[] num = new int[N + 1];

        int startNum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            num[i] = num[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = K; i <= N; i++) {
            MAX = Math.max(num[i] - num[i - K], MAX);
        }

        sb.append(MAX);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
