package BJ01000;

import java.io.*;
import java.util.PriorityQueue;

public class BJ01927_최소힙 {
    private static int N, num;  // 연산 정보
    private static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        pq = new PriorityQueue<>();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            if (0 < num) {
                pq.add(num);
                continue;
            } else if(pq.isEmpty()) {
                pq.add(0);
            }

            // 자연수가 아닐 경우 맨 앞에 있는 값 (가장 작은 값) 출력
            sb.append(pq.poll()).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
