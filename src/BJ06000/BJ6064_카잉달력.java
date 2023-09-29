package BJ06000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ6064_카잉달력 {
    private static int T, M, N, x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if (x == y) sb.append(x);
            else sb.append(calYear());
            sb.append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int calYear() {
        int i = x;
        for (; i <= M * N; i += M) {  // 연도
            if (((i % M == 0) ? M : i % M) == x)
                //System.out.println(i + "년 연도 <" + ((i % M == 0) ? M : i % M) + ":" + ((i % N == 0) ? N : i % N) + ">");
            if (((i % N == 0) ? N : i % N) == y) return i;
        }
        if (((i % N == 0) ? N : i % N) == y) return i;
        else return -1;
    }


}
