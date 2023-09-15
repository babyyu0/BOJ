package BJ10000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ10866_덱 {
    private static ArrayDeque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.pollFirst()).append("\n");
                    break;
                case "pop_back":
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.pollLast()).append("\n");
                    break;
                case "size":
                    sb.append(deque.size()).append("\n");
                    break;
                case "empty":
                    sb.append((deque.isEmpty())? "1":"0").append("\n");
                    break;
                case "front":
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.peekFirst()).append("\n");
                    break;
                case "back":
                    if(deque.isEmpty()) sb.append("-1\n");
                    else sb.append(deque.peekLast()).append("\n");
                    break;
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
