package BJ02000;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class BJ02108_통계학 {
    private static int N;
    private static int[] numArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        numArr = new int[N];
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(numArr);

        System.out.println(arithmeticMean());   // 산술 평균
        System.out.println(median());           // 중앙값
        System.out.println(mode());             // 최빈값
        System.out.println(range());            // 범위
    }



    private static int arithmeticMean() {
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += numArr[i];
        }
        return (int) Math.round(sum / N);
    }

    private static int median() {
        return numArr[(int) Math.floor(N / 2)];
    }

    private static int mode() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(numArr[0]);
        int maxCnt = 1, cnt = 1;

        for (int i = 1; i < N; i++) {
            if(numArr[i] == numArr[i - 1]) cnt++;
            else cnt = 1;

            if(maxCnt < cnt) {
                maxCnt = cnt;
                list.clear();
                list.add(numArr[i]);
            } else if (maxCnt == cnt) {
                list.add(numArr[i]);
            }
        }
        Collections.sort(list);
        return (2 <= list.size())? list.get(1) : list.get(0);
    }

    private static int range() {
        return Math.abs(numArr[N - 1] - numArr[0]);
    }
}
