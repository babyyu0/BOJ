package BJ10000;

import java.io.*;
import java.util.Arrays;

public class BJ10989_수정렬하기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());  // 수의 개수 N
        int[] numArr = new int[N];

        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numArr);

        for (int i = 0; i < N; i++) {
            sb.append(numArr[i]).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
