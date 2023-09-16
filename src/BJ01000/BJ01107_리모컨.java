package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BJ01107_리모컨 {

    private static final int CHANNEL = 100;
    private static int N, M, minNum;
    private static boolean[] broke;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        if (N == CHANNEL) {
            System.out.println(0);
            return;
        }

        M = Integer.parseInt(br.readLine().trim());
        broke = new boolean[12];
        if (M != 0) {
            // 고장난 버튼
            StringTokenizer st = new StringTokenizer(br.readLine());
            String btn;
            for (int i = 0; i < M; i++) {
                broke[Integer.parseInt(st.nextToken())] = true;
            }
        }

        minNum = Math.abs(N - CHANNEL);
        // System.out.println("1 : " + minNum);

        minNum = Math.min(minNum, plusChannel(0));
        // System.out.println("2 : " + minNum);

        minNum = Math.min(minNum, minusChannel(0));
        // System.out.println("3 : " + minNum);
        System.out.println(minNum);
    }

    private static int plusChannel(int count) {
        while (true) {
            if (0 <= N - count && checkChannel(Integer.toString(N - count)) || minNum < Integer.toString(N - count).length() + count)
                return Integer.toString(N - count).length() + count;
            count++;
        }
    }

    private static int minusChannel(int count) {
        while (true) {
            if (checkChannel(Integer.toString(N + count)) || minNum < Integer.toString(N + count).length() + count)
                return Integer.toString(N + count).length() + count;
            count++;
        }
    }

    private static boolean checkChannel(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (broke[Integer.parseInt(num.substring(i, i + 1))]) return false;
        }
        return true;
    }
}