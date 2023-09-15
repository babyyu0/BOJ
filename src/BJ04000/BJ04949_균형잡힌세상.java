package BJ04000;

import java.io.*;
import java.util.Stack;

public class BJ04949_균형잡힌세상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        boolean isAvaliable;
        Stack<Character> stack = new Stack<>();
        String str;
        char[] charArr;

        while(true) {
            isAvaliable = true;
            stack.clear();

            str = br.readLine();
            if(str.equals(".")) break;
            str = str.replaceAll("[\\w\\s.]", "");

            charArr = str.toCharArray();

            for (int i = 0; i < charArr.length; i++) {
                if(charArr[i] == ')') {
                    if(!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                        continue;
                    } else {
                        isAvaliable = false;
                        break;
                    }
                }
                else if(charArr[i] == '}') {
                    if(!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                        continue;
                    } else {
                        isAvaliable = false;
                        break;
                    }
                }
                else if(charArr[i] == ']') {
                    if(!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                        continue;
                    } else {
                        isAvaliable = false;
                        break;
                    }
                } else {
                    stack.push(charArr[i]);
                }
            }
            if(!stack.isEmpty()) isAvaliable = false;
            sb.append((isAvaliable)? "yes" : "no").append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
