package BJ01000;

import java.io.*;
import java.math.BigInteger;

public class BJ01247_부호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N; BigInteger sum;
        for (int test_case = 0; test_case < 3; test_case++) {
            N = Integer.parseInt(br.readLine());
            sum = BigInteger.ZERO;

            for (int i = 0; i < N; i++) {
                sum = sum.add(new BigInteger(br.readLine()));
                // System.out.println(sum);
            }

            if(sum == BigInteger.ZERO) sb.append("0");
            else {
                sb.append((sum.compareTo(BigInteger.ZERO) < 0)? "-": "+");
            }
            sb.append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
