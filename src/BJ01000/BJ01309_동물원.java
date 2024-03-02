package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ01309_동물원 {
    private static int N;
    private static long[] cases, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cases = new long[N + 1];  // 0: 왼쪽에 놓았을 경우, 1: 오른쪽에 놓았을 경우
        sum = new long[N + 1];
        cases[1] = sum[1] = 2; // N = 1일 때 놓을 수 있는 경우의 수

        countCases();
        //printCases();
        System.out.println(cases[N] + 1);
    }

    private static void countCases() {
        for (int i = 2; i <= N; i++) {
            cases[i] = (cases[i - 2] + (cases[i - 1] * 2) + 2) % 9901;
        }
    }

    private static void printCases() {
        for (int i = 1; i <= N; i++) {
            System.out.printf("N = %3d일 경우 경우의 수: %-3d\n", i, cases[i]);
        }
    }
}
