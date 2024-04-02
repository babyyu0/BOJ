package BJ10000;

import java.io.*;
import java.util.Arrays;

public class BJ10610_30 {
    private static char[] N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = br.readLine().toCharArray();
        Arrays.sort(N);
        if (N[0] != '0') {
            System.out.println(-1);
            return;
        }
        long sum = 0;
        for (int i = N.length - 1; 0 <= i; i--) {
            sb.append(N[i]);
            sum += (int) N[i];
        }

        if (sum % 3 == 0) {
            bw.append(sb.toString());
        } else {
            bw.append(Integer.toString(-1));
        }
        bw.flush();
        bw.close();
        br.close();

    }
}
