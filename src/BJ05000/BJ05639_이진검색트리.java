package BJ05000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Stack;

public class BJ05639_이진검색트리 {

    private static BufferedReader br;
    private static StringBuilder sb;
    private static Stack<Long> stack;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        map = new HashMap<>();
        stack = new Stack<>();

        stack.add((long) Math.pow(10, 6));
        stack.add(Long.parseLong(br.readLine()));
        dfs();

        while (stack.size() != 1) {
            sb.append(stack.pop()).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs() throws IOException {
        String str = br.readLine();
        if (str == null || str.isEmpty()) {
            return;
        }
        long num = Long.parseLong(str);
        if (stack.peek() > num) {  // 왼쪽 노드일 경우
            // System.out.println(num + " 진입 1");
            // beforeIndex *= 2;
            // map.put(beforeIndex, num);
            stack.add(num);
            // MAX = Math.max(MAX, beforeIndex);
            dfs();
        } else {
            // System.out.println(num + " 진입 2");
            while (true) {
                long tmp = stack.pop();
                // System.out.println(num + "과 인덱스 [" + beforeIndex + "] 비교");
                if (stack.peek() > num) {
                    // beforeIndex = beforeIndex * 2 + 1;
                    // map.put(beforeIndex, num);
                    stack.add(tmp);
                    stack.add(num);
                    break;
                } else {
                    sb.append(tmp).append("\n");
                    // System.out.println("stack : " + tmp);
                    // beforeIndex /= 2;
                }
            }
            dfs();
        }
    }
}

