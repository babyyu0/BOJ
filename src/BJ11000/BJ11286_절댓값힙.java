package BJ11000;

import java.io.*;
import java.util.PriorityQueue;

public class BJ11286_절댓값힙 {
    private static int N;
    private static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(o1) == Math.abs(o2)) {
                return (o1 < o2) ? -1 : 1;
            }
            return (Math.abs(o1) < Math.abs(o2)) ? -1 : 1;
        });

        N = Integer.parseInt(br.readLine());

        long num;
        for (int i = 0; i < N; i++) {
            num = Long.parseLong(br.readLine());
            if (num == 0) {
                sb.append((pq.size() != 0) ? pq.poll() : 0).append("\n");
            } else {
                pq.add(num);
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
