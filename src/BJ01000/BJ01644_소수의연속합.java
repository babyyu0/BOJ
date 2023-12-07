package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BJ01644_소수의연속합 {
    private static int N, NUM;
    private static boolean[] isPrime;
    private static List<Integer> primeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        primeList = new ArrayList<>();

        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        checkPrime(0);
        // System.out.println(primeList);
        System.out.println(NUM);
    }

    private static void checkPrime(int sum) {
        for (int i = 2; i <= N; i++) {
            if(isPrime[i]) {
                primeList.add(i);
                sum += i;
                while (N < sum) {
                    sum -= primeList.remove(0);
                }

                if(N == sum) {
                    // System.out.println(primeList);
                    NUM++;
                }
                for (int j = 2; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }
    }
}
