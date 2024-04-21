package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ01748_수이어쓰기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long count = 1;
        long sum = 0;
        while (0 < (N / (int) Math.pow(10, count))) {
            sum += (int) (Math.pow(10, count) - Math.pow(10, count - 1)) * count;
            count++;
        }
        //countLen(N);
        System.out.println(sum + (N - (int) Math.pow(10, count - 1) + 1) * count);
    }

    private static void countLen(long n) {
        String str = "";
        for (long i = 1; i <= n; i++) {
            str += i;
        }
        System.out.println(str.length());
    }
}
