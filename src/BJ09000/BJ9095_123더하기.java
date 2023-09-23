package BJ09000;

import java.io.*;

public class BJ9095_123더하기 {
    private static int T;
    private static long cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            cnt = 0;
            countNum(0, Integer.parseInt(br.readLine()));
            sb.append(cnt).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void countNum(int sum, int num) {
        if(num < sum) return;
        else if (num == sum) {
            cnt++; return;
        }
        countNum(sum + 1, num);
        countNum(sum + 2, num);
        countNum(sum + 3, num);
    }
}
