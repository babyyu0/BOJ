package BJ09000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class BJ09935_문자열폭발 {

    private static String string;
    private static String findString;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        string = br.readLine().trim();
        findString = br.readLine().trim();

        findRest();
    }

    private static void findRest() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Char> deque = new ArrayDeque<>();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == findString.charAt(0)) { // 첫번째문자열 저장
                if (1 < findString.length()) {
                    deque.add(new Char(0, string.charAt(i)));
                }
            } else if (!deque.isEmpty()
                    && findString.charAt(deque.peekLast().findAt + 1) == string.charAt(i)) {
                if (deque.peekLast().findAt + 1 == findString.length() - 1) {
                    for (int j = 1; j < findString.length(); j++) {
                        deque.pollLast();
                    }
                } else {
                    deque.add(new Char(deque.peekLast().findAt + 1, string.charAt(i)));
                }
            } else {
                while (!deque.isEmpty()) {
                    sb.append(deque.pollFirst().character);
                }
                sb.append(string.charAt(i));
            }
        }
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst().character);
        }

        if (sb.length() == 0) {
            sb.append("FRULA");
        }
        sb.append("\n");
        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }

    private static class Char {

        int findAt;
        char character;

        private Char(int findAt, char character) {
            this.findAt = findAt;
            this.character = character;
        }
    }
}
