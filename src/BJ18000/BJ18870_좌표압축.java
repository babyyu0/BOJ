package BJ18000;

import java.io.*;
import java.util.*;

public class BJ18870_좌표압축 {
    private static class Number {
        Number(long num, int index, int order) {
            this.num = num;
            this.index = index;
            this.order = order;
        }
        long num;
        int index;
        int order;
    }
    private static int N;
    private static Number[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new Number[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            // 순서 넣기
            numbers[i] = new Number(Long.parseLong(st.nextToken()), i, 0);
        }

        Arrays.sort(numbers, Comparator.comparingLong(o -> o.num));

        int order = 0;
        numbers[0].order = order;
        for (int i = 1; i < N; i++) {
            if(numbers[i - 1].num == numbers[i].num) numbers[i].order = order;
            else numbers[i].order = ++order;
        }
        Arrays.sort(numbers, Comparator.comparingInt(o -> o.index));

        for (int i = 0; i < N; i++) {
            sb.append(numbers[i].order).append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
