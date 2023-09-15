package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02609_최대공약수와최대공배수 {

    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        System.out.println(maxDevide(Math.max(N, M)));
        System.out.println(minMultiply());
    }

    private static int minMultiply() {
        for(int i = 1;; i++) {
            if (i % N == 0 && i % M == 0) return i;
        }
    }

    private static int maxDevide(int max) {  // 최대공약수
        for (int i = max; i >= 1; i--) {
            if(N % i == 0 && M % i == 0) return i;
        }
        return 1;
    }
}
