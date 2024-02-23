package BJ11000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11066_파일합치기 {
    private static final int MAX_COUNT = 500;
    private static int K; // 소설 장의 수
    private static long[] files;  // 각 파일의 크기
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        files = new long[MAX_COUNT + 2];
        dp = new long[MAX_COUNT + 2][MAX_COUNT + 2];

        for (int tc = 0; tc < T; tc++) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                files[i] = files[i - 1] + Long.parseLong(st.nextToken());  // 파일 크기 배열 초기화
                Arrays.fill(dp[i], Long.MAX_VALUE);  // DP 배열 초기화
                dp[i][i] = 0;  // 자기 자신의 시간 비용 0
            }
            sb.append(findMinFileSize()).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long findMinFileSize() {
        for (int i = 1; i <= K; i++) {  // 까지
            for (int j = i - 1; 1 <= j; j--) {  // 에서
                //System.out.println("C" + j + "에서 C" + i + "까지");
                if(i - j == 1) {  // 하나 차이가 날 경우
                    dp[j][i] = Math.min(files[i] - files[j - 1], dp[j][i]);  // 시간 비용은 하나 뿐
                    continue;
                }
                for (int k = j; k < i; k++) {  // x(j, k) 파일과 x(k + 1, i) 파일 합치기
                    //System.out.println("x(" + j + ", " + k + ") 파일과 " + "x(" + (k + 1) + ", " + i + ") 파일 합치기...");
                    dp[j][i] = Math.min(dp[j][k] + dp[k + 1][i] + files[k] - files[j - 1] + files[i] - files[k], dp[j][i]);
                }
            }
        }

        return dp[1][K];
    }
}
