package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BJ11444_피보나치수6 {

    // fibo(n) = Math.pow(fibo((n+1) / 2), 2) + Math.pow(fibo((n - 1 / 2)), 2)
    private static long N;
    private static final long DIV = 1000000007L;
    private static Map<Long, Long> fibo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fibo = new HashMap<>();

        N = Long.parseLong(br.readLine());
        System.out.println(fibo(N, 0));
        // System.out.println(fibo);
    }

    private static long fibo(long num, long k) {
        if(num == 0L) return 0L;
        else if(num == 1L || num == 2L) return 1L;
        else if (fibo.containsKey(num)) return fibo.get(num);

        if(num % 2L == 0) {
            k = num / 2L;
            long tmp1 = fibo(k - 1L, 0);
            long tmp2 = fibo(k, 0);
            fibo.put(num, ((tmp1 * 2L + tmp2) * tmp2) % DIV);
        } else {
            long tmp1 = fibo((num + 1L) / 2L, 0);
            long tmp2 = fibo((num - 1L) / 2L, 0);
            fibo.put(num, (tmp1 * tmp1 + tmp2 * tmp2) % DIV);
        }

        return fibo.get(num);
    }
}
