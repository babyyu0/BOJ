package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ01715_카드정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        long answer = 0;
        long make;
        while(1 < pq.size()) {
            //System.out.println(pq);
            make = pq.poll() + pq.poll();
            answer += make;
            pq.add(make);
            //System.out.println("answer: " + answer);
        }

        System.out.println(answer);
    }
}
