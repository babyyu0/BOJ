package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01918_후위표기식 {
    private static class Operator implements Comparable<Operator> {
        protected Operator(int index, char operator, int plusRank) {
            this.index = index;
            this.operator = operator;
            this.plusRank = plusRank;
        }

        int index;
        char operator;
        int plusRank;

        @Override
        public String toString() {
            return "Operator{" +
                    "index=" + index +
                    ", operator=" + operator +
                    ", plusRank=" + plusRank +
                    '}';
        }

        @Override
        public int compareTo(Operator o) {
            if (this.plusRank < o.plusRank) return 1;  // 괄호가 더 많은 게 더 앞으로
            else if (this.plusRank > o.plusRank) return -1;
            else if (rank.get(this.operator) < rank.get(o.operator)) return 1;  // 연산자 순위가 더 높은 게 더 앞으로
            else if (rank.get(this.operator) > rank.get(o.operator)) return -1;
            return Integer.compare(this.index, o.index);
        }
    }

    private static String mathString;
    private static Map<Character, Integer> rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        rank = new HashMap<>() {{  // 순위 매기기
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
        }};

        mathString = br.readLine().trim();
        Queue<Character> numQueue = new ArrayDeque<>();
        PriorityQueue<Operator> opPriorityQueue = new PriorityQueue<>();

        String operators = "";
        char charAt;
        int deepRank = 0;
        for (int i = 0; i < mathString.length(); i++) {
            charAt = mathString.charAt(i);  // 한 글자 씩 빼내기
//            System.out.println(numQueue);
//            System.out.println(opPriorityQueue);
//            System.out.println();
            switch (charAt) {
                case '(':
                    deepRank++;  // 괄호 몇 개 안에 있는지 알려주기
                    break;
                case ')':
                    deepRank--;  // 괄호 몇개 밖에 있는지 알려주기
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    if (opPriorityQueue.peek() != null && 0 >= opPriorityQueue.peek().compareTo(new Operator(i, charAt, deepRank))) {
                        while (!numQueue.isEmpty()) {
                            sb.append(numQueue.poll());
                        }
                        while (!opPriorityQueue.isEmpty() && 0 >= opPriorityQueue.peek().compareTo(new Operator(i, charAt, deepRank))) {
                            sb.append(opPriorityQueue.poll().operator);
                        }
                    }
                    opPriorityQueue.add(new Operator(i, charAt, deepRank));
                    break;
                default:
                    numQueue.add(charAt);
                    break;
            }
        }

        while (!numQueue.isEmpty()) {
            sb.append(numQueue.poll());
        }
        while (!opPriorityQueue.isEmpty()) {
            sb.append(opPriorityQueue.poll().operator);
        }

        bw.append(sb.toString());
        bw.close();
        br.close();
    }


}
