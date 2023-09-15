package BJ01000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ01003_피보나치함수 {

    private static int T;
    private static long[][] fib;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        fib = new long[40 + 1][2];
        fibonacci();


        int num;
        for (int test_case = 0; test_case < T; test_case++) {
            num = Integer.parseInt(br.readLine());
            sb.append(fib[num][0]).append(" ").append(fib[num][1]).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void fibonacci() {
        fib[0][0] = 1;
        fib[0][1] = 0;
        
        fib[1][0] = 0;
        fib[1][1] = 1;
        
        for (int i = 2; i <= 40; i++) {
            fib[i][0] = fib[i - 1][0] + fib[i - 2][0];
            fib[i][1] = fib[i - 1][1] + fib[i - 2][1];
        }
    }
}