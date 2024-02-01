package BJ03000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ03036_링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 링의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        int firstRing = Integer.parseInt(st.nextToken());

        int compRing, numerator, denominator; // 분자 분모
        for (int i = 1; i < N; i++) {
            compRing = Integer.parseInt(st.nextToken());
            numerator = firstRing;
            denominator = compRing;

            for (int j = 2; j <= Math.max(numerator, denominator); j++) {
                if(numerator % j == 0 && denominator % j == 0) {
                    numerator /= j;
                    denominator /= j;
                    j = 1;
                }
            }

            System.out.println(numerator + "/" + denominator);
        }


    }
}
