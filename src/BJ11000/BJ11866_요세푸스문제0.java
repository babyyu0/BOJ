package BJ11000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11866_요세푸스문제0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Queue<Integer> queue = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());  // 뽑을 번째 수

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        int cnt = 0;
        sb.append("<");
        while(queue.size() > 1) {
            if(++cnt == K) {
                cnt = 0;
                sb.append(queue.poll()).append(", ");
            } else {
                queue.add(queue.poll());
            }
        }
        sb.append(queue.poll()).append(">");

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
