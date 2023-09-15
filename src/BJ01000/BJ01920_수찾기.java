package BJ01000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01920_수찾기 {

    private static int N, mid;
    private static long[] numArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numArr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(numArr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            sb.append(binarySearch(Long.parseLong(st.nextToken()), 0, N - 1)).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static int binarySearch(long selNum, int start, int end) {
        mid = (start + end) / 2;

        if(numArr[mid] == selNum) return 1;
        else if(start >= end) return 0;
        else if(numArr[mid] < selNum) return binarySearch(selNum, mid + 1, end);
        else if(numArr[mid] > selNum) return binarySearch(selNum, start, mid - 1);
        return 0;
    }
}
