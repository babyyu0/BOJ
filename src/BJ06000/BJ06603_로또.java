package BJ06000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ06603_로또 {
    private static int k;
    private static final int INF = 13;
    private static int[] S;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        S = new int[INF];
        while (k != 0) {
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = k; i < INF; i++) {
                S[i] = 0;
            }
            Arrays.sort(S);

            combination(INF - k, 0, "");
            sb.append("\n");

            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int start, int count, String str) {
        if (count == 6) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = start; i < INF; i++) {
            combination(i + 1, count + 1, String.format("%s%d ", str, S[i]));
        }
    }
}