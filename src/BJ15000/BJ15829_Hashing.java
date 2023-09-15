package BJ15000;

import java.io.*;

public class BJ15829_Hashing {

    private static final long multiplyNum = 31;
    private static final long M = 1234567891;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long sum = 0;

        char[] str = br.readLine().toCharArray();
        long num = 0;
        for (int i = 0; i < N; i++) {
            num  = str[i] - 96;
            for (int j = 0; j < i; j++) {
                num = num * 31 % M;
            }
            sum += num;
        }

        System.out.println(sum % M);

    }
}
