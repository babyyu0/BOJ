package BJ11000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11723_집합 {
    private static int M;
    private static boolean[] S;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st;
        S = new boolean[21];

        M = Integer.parseInt(br.readLine());  // 명령 수

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    add(Integer.parseInt(st.nextToken()));
                    break;
                case "remove":
                    remove(Integer.parseInt(st.nextToken()));
                    break;
                case "check":
                    check(Integer.parseInt(st.nextToken()));
                    break;
                case "toggle":
                    toggle(Integer.parseInt(st.nextToken()));
                    break;
                case "all":
                    all();
                    break;
                case "empty":
                    empty();
                    break;
                default:
                    break;
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void add(int x) {
        S[x] = true;
    }

    private static void remove(int x) {
        S[x] = false;
    }

    private static void check(int x) {
        sb.append((S[x])? 1 : 0).append("\n");
    }

    private static void toggle(int x) {
        S[x] = !S[x];
    }

    private static void all() {
        Arrays.fill(S, true);
    }

    private static void empty() {
        Arrays.fill(S, false);
    }
}
