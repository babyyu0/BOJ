package BJ01000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ01024_수열의합 {
    // private static long N;
    private static int N, L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        long tmp, sum;
        boolean flag = false;
        while(L <= 100) {
            sb.setLength(0);
            sum = 0L;
            tmp = N / L - (L / 2);
            for (long i = 0; i < L; i++) {
                sum += tmp;
                sb.append(tmp++).append(" ");
            }
            if(sum == N) {
                flag = true;
                break;
            }

            sb.setLength(0);
            sum = 0L;
            tmp = N / L - (L / 2) + 1;
            for (long i = 0; i < L; i++) {
                sum += tmp;
                sb.append(tmp++).append(" ");
            }
            if(sum == N) {
                flag = true;
                break;
            }
            L++;
        }

        bw.append(!flag || sb.charAt(0) == '-' ? "-1" : sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
