package BJ01000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01516_게임개발 {
    private static int N;
    private static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        times = new int[N + 1];

        int time, first;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time = Integer.parseInt(st.nextToken());
            times[i] += time;
            while(0 < (first = Integer.parseInt(st.nextToken()))) {
                times[i] += times[first];
            }
        }

        System.out.println(Arrays.toString(times));
    }
}
