package BJ17000;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ17413_단어뒤집기2 {
    private static Stack<Character> stack;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        String str = br.readLine();
        stack = new Stack<>();

        boolean flag = false;
        int i = 0;
        while(i < str.length()) {
            switch (str.charAt(i)) {
                case '<':
                    flag = true;
                    popWords();  // 글자 거꾸로 다 빼내기
                    sb.append("<");
                    break;
                case '>':
                    sb.append(">");
                    flag = false;
                    break;
                case ' ':
                    if(!flag) {
                        popWords();  // 글자 거꾸로 다 빼내기
                        sb.append(" ");
                        break;
                    }
                default:
                    if(flag) { // 괄호 안일 경우
                        sb.append(str.charAt(i));
                    } else {  // 괄호 밖일 경우
                        stack.push(str.charAt(i));
                    }
            }
            i++;
        }

        popWords();
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void popWords() {
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }
}
