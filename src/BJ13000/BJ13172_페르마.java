package BJ13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13172_페르마 {
    private static final long MOD = 1000000007;
    // private static final double MOD = 11.0 ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int M = Integer.parseInt(br.readLine());

        // 분모, 분자
        long N, S; long denominator = 1L, numerator = 0L;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());
            S = Long.parseLong(st.nextToken());

            numerator = (numerator * N) + (S * denominator);  // 분자 통분
            denominator *= N;  // 분모 통분

            numerator %= MOD;
            denominator %= MOD;
        }

        long sum = 0;
        if(numerator % denominator != 0) {
            sum += (arrange(denominator, MOD - 2) * numerator);
        } else {
            sum += denominator / numerator;
            System.out.println(sum);
        }

        sum %= MOD;
        System.out.println((long) sum);
    }

    private static long arrange(long N, long index) {
        if(index == 1) {
            return N;
        }

        long tmp = arrange(N, index / 2);
        if(index % 2 == 1) {
            return tmp * tmp % MOD * N % MOD;
        } else {
            return tmp * tmp % MOD;
        }
    }
}
