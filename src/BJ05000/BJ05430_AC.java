package BJ05000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ05430_AC {
    private static boolean flag;  // true일 경우 정방향, false일 경우 역방향
    private static int T, N;
    private static char[] chars;
    private static ArrayDeque<Integer> deque;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        deque = new ArrayDeque<>();

        T = Integer.parseInt(br.readLine());  // 테스트 케이스
        for (int test_case = 0; test_case < T; test_case++) {
            flag = true;  // 정방향 전환
            deque.clear();  // 큐 비우기

            chars = br.readLine().toCharArray();  // 함수 받기
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", " ")
            );
            for (int i = 0; i < N; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            if(!runFunc()) {
                sb.append("error\n");
                continue;
            }

            sb.append("[");
            if(flag) printFromLeft(sb);
            else printFromRight(sb);
            sb.append("]\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean runFunc() {
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'R':
                    flag = !flag;
                    break;
                case 'D':
                    if(deque.isEmpty()) return false;
                    else if(flag) deque.pollFirst();
                    else deque.pollLast();
                    break;
                default:
                    break;

            }
        }

        return true;
    }

    private static void printFromLeft(StringBuilder sb) {
        while (!deque.isEmpty()) sb.append(deque.pollFirst()).append((!deque.isEmpty())? ",":"");
    }
    private static void printFromRight(StringBuilder sb) {
        while (!deque.isEmpty()) sb.append(deque.pollLast()).append((!deque.isEmpty())? ",":"");
    }
}
