package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ02504_괄호의값 {
    private static int index = 0;
    private static String str;
    private static Stack<String> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        stack = new Stack<>();

         // (1) [3]
        int num;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '(' || str.charAt(i) == '[') {
                stack.add(str.substring(i, i + 1));
            } else if (str.charAt(i) == ')') {
                num = 0;
                while(!stack.isEmpty() && stack.peek().matches("[0-9]{1,}")) {  // 숫자일 때까지
                    num += Integer.parseInt(stack.pop());
                }
                num = Math.max(2, num * 2);

                if (stack.isEmpty() || !stack.pop().equals("(")) {
                    System.out.println(0); return;
                }

                stack.add(Integer.toString(num));
            } else if (str.charAt(i) == ']') {
                num = 0;
                while(!stack.isEmpty() && stack.peek().matches("[0-9]{1,}")) {  // 숫자일 때까지
                    num += Integer.parseInt(stack.pop());
                }
                num = Math.max(3, num * 3);

                if (stack.isEmpty() || !stack.pop().equals("[")) {
                    System.out.println(0); return;
                }

                stack.add(Integer.toString(num));
            }

            System.out.println(stack);
        }
        num = 0;
        while (!stack.isEmpty()) {
            if(!stack.peek().matches("[0-9]{1,}")) {
                System.out.println(0); return;
            }
            num += Integer.parseInt(stack.pop());
        }
        System.out.println(num);
        //System.out.println("성공");
    }
}
