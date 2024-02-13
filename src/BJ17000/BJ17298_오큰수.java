package BJ17000;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ17298_오큰수 {

    private static int[] A, rightBigNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());  // 수열 A의 크기
        A = new int[N];
        rightBigNum = new int[N];
        Arrays.fill(rightBigNum, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        A[0] = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            //System.out.println(A[stack.peek()] + " < " + A[i] + "?");

            while(!stack.isEmpty() && A[stack.peek()] < A[i]) {
                rightBigNum[stack.pop()] = A[i];
            }

            stack.push(i);
            //System.out.println(Arrays.toString(rightBigNum));
        }

        for (int i = 0; i < N; i++) {
            sb.append(rightBigNum[i]).append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
