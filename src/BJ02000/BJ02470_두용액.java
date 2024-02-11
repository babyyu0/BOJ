package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ02470_두용액 {
    private static long[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        numbers = new long[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(numbers);

        long compNum;
        long MIN = 200000;
        long[] selected = new long[2];
        for (int i = 0; i < numbers.length; i++) {
            compNum = binarySearch(numbers[i], 0, numbers.length - 1);
            //System.out.println(numbers[i] + "와 " + compNum + "이 제일 적게 차이 남");

            if (Math.abs(compNum + numbers[i]) < MIN) {
                selected[0] = Math.min(numbers[i], compNum);
                selected[1] = Math.max(numbers[i], compNum);
                MIN = Math.abs(compNum + numbers[i]);
            }
        }

        System.out.println(selected[0] + " " + selected[1]);
    }

    private static long binarySearch(long N, int start, int end) {
        int calIndex = (start + end) / 2;

        //System.out.println("비교 기준인 " + (-N) + "과 " + numbers[start] + " ~ " + numbers[end] + " 사이의 값 " + numbers[calIndex] + "를 비교");
        if (end - start <= 1) {
            return numbers[start] == N ? numbers[end] : numbers[start];
        } else if (numbers[calIndex] == N) {
            if (N < 0) return binarySearch(N, start + 1, end);
            else return binarySearch(N, start, end - 1);
        } else if (-N < numbers[calIndex]) {
            return binarySearch(N, start, calIndex);
        } else if (numbers[calIndex] < -N) {
            return binarySearch(N, calIndex, end);
        }

        return numbers[calIndex];
    }
}
