package BJ01000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ01406_에디터 {
    private static ArrayDeque<Character> frontDeque, backDeque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        frontDeque = new ArrayDeque<>();
        backDeque = new ArrayDeque<>();
        for (char msgChar: br.readLine().toCharArray()) {
            frontDeque.addLast(msgChar);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "L":
                    curserLeft();
                    break;
                case "D":
                    curserRight();
                    break;
                case "B":
                    delete();
                    break;
                case "P":
                    add(st.nextToken().charAt(0));
                    break;
            }
            //print();
        }
        bw.append(print());
        bw.flush();
        bw.close();
        br.close();

    }
    private static void curserLeft() {
        if(frontDeque.isEmpty()) return;
        backDeque.addFirst(frontDeque.removeLast());
    }
    private static void curserRight() {
        if(backDeque.isEmpty()) return;
        frontDeque.addLast(backDeque.removeFirst());
    }

    private static void delete() {
        if(frontDeque.isEmpty()) return;
        frontDeque.removeLast();
    }

    private static void add(char msg) {
        frontDeque.addLast(msg);
    }

    private static String print() {
        StringBuilder sb = new StringBuilder();
        while (!frontDeque.isEmpty()) {
            sb.append(frontDeque.poll());
        }
        //System.out.print("I");
        while (!backDeque.isEmpty()) {
            sb.append(backDeque.poll());
        }
        return sb.toString();
    }
}
