package BJ02000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ02448_별찍기11 {

    private static int N;
    private static boolean[][] star;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        star = new boolean[N + 1][N / 3 * 6];

        for (int i = 0; i < N / 3 * 6; i++) {
            if (i % 6 != 0) {
                star[N][i] = true;
            }
        }

        printStar(1);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < N / 3 * 6; j++) {
                sb.append((star[i][j]) ? "*" : " ");
            }
            sb.append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void printStar(int count) {
        if (N == count) {
            return;
        }

        printStar(count + 1);
        boolean flag = false;
        if (count % 3 == 2) { // 2개일 경우
            for (int i = N - count; i < N / 3 * 6 - (N - count); i++) {
                if (!flag && star[count + 1][i] && star[count + 1][i - 1]) {
                    flag = true;
                } else if (flag && star[count + 1][i]) {
                    flag = false;
                }

                star[count][i] = flag;
            }
        } else if (count % 3 == 1) { // 1개일 경우
            for (int i = N - count; i < N / 3 * 6 - (N - count); i++) {
                if (!flag && star[count + 1][i - 1] && star[count + 1][i + 1]) {
                    flag = true;
                } else {
                    flag = false;
                }

                star[count][i] = flag;
            }
        } else { // 5개일 경우
            for (int i = N - count; i < N / 3 * 6 - (N - count); i++) {
                if (!flag && star[count + 1][i - 1]) {
                    flag = true;
                } else if (flag && (star[count + 1][i])) {
                    flag = false;
                    i++;
                } else if (flag && 0 <= i - 6 && !star[count][i - 6] && star[count][i - 5]) {
                    i++;
                }

                star[count][i] = flag;
            }
        }
    }
}
