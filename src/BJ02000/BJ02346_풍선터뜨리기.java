package BJ02000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ02346_풍선터뜨리기 {
    private static class Balloon {
        public Balloon(int index, int move) {
            this.index = index;
            this.move = move;
        }

        int index, move;
    }
    public static void main(String[] args) throws IOException {
        ArrayDeque<Balloon> deque = new ArrayDeque<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int i = 1;
        while(st.hasMoreTokens()) {
            deque.add(new Balloon(i++, Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        Balloon balloon;
        while(deque.size() != 1) {
            //System.out.println(deque);
            balloon = deque.poll();
            sb.append(balloon.index).append(" ");
            if(balloon.move < 0) {  // 왼쪽 이동
                while(balloon.move++ != 0) {
                    deque.addFirst(deque.pollLast());
                }
            } else if (0 < balloon.move) {  // 오른쪽 이동
                while(--balloon.move != 0) {
                    deque.addLast(deque.pollFirst());
                }
            }
        }
        balloon = deque.poll();
        sb.append(balloon.index).append(" ");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
