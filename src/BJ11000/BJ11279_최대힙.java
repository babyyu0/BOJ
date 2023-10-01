package BJ11000;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ11279_최대힙 {
    private static int N;
    private static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        pq = new PriorityQueue<>(Collections.reverseOrder());
        N = Integer.parseInt(br.readLine());

        long x;
        for (int i = 0; i < N; i++) {
            x = Long.parseLong(br.readLine());
            if (x == 0) {
                sb.append((pq.isEmpty()) ? 0 : pq.poll()).append("\n");
            } else {
                pq.add(x);
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
