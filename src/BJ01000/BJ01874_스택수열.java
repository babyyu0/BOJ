package BJ01000;

import java.io.*;
import java.util.Stack;

public class BJ01874_스택수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        int[] numArr = new int[N];

        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            sb.append("+\n");  // push 출력
            stack.push(i);  // 스택에 push

            while (!stack.isEmpty() && stack.peek() == numArr[cnt]) {  // 수열의 숫자가 같을 때까지
                sb.append("-\n");  // pop 출력
                stack.pop();  // 스택에 pop
                cnt++;
            }
        }

        if(stack.isEmpty()) bw.append(sb.toString());
        else bw.append("NO");
        bw.flush();
        bw.close();
        br.close();
    }
}
