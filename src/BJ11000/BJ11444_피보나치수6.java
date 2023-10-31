package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11444_피보나치수6 {

    // fibo(n) = Math.pow(fibo((n+1) / 2)) + Math.pow(fibo((n - 1 / 2)), 2)
    private static long N;
    private static long[] fibo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        fibo = new long[1000000];
        fibo(N, 0, 0);
    }

    private static long fibo(long num, long fibo1, long fibo2) {
        if (num == 1 || num == 2) {
            return 1;
        }
        if (num == 0) {
            return 0;
        }
        if (1000000 <= num || fibo[(int) num] == 0) {
            fibo1 = fibo((num + 1) / 2L, 0, 0);
            if((num + 1) / 2L < 1000000) fibo[(int)((num + 1) / 2L)] = fibo1;
            fibo2 = fibo((num - 1) / 2L, 0, 0);
            if((num - 1) / 2L < 1000000) fibo[(int)((num + 1) / 2L)] = fibo2;
        }

        ret
    }
}
