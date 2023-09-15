package BJ02000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ02805_나무자르기 {

    private static int N; // 나무의 수
    private static long M;  // 가져가야하는 나무의 수
    private static long[] trees;  // 나무 길이 배열
    private static long meter; // 잘린 나무 도합
    private static long MAX;  // 최대값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        trees = new long[N];

        long max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            
            // 나무의 최대 길이 저장
            max = Math.max(trees[i], max);
        }

        binarySearch(max, max);

        sb.append(MAX);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void binarySearch(long len/* 나무 길이 */, long size/* 자르거나 더할 사이즈 */) {
        System.out.println("나무 길이 : " + len + ", 사이즈 : " + size);

        meter = 0;
        for (int i = 0; i < N; i++) {
            meter += (trees[i] - len >= 0)? (trees[i] - len) : 0;
        }
        if(size == 1) {
            if(meter >= M) MAX = Math.max(MAX, len);
            return;
        }

        if(meter < M) {  // 길이를 충족하지 못할 경우
            if(size % 2 == 1) {  // 홀수일 경우
                binarySearch(len - 1, size - 1);  // 짧게 자르기
            } else {  // 짝수일 경우
                binarySearch(len - (size / 2), size / 2);  // 짧게 자르기
            }
        } else {  // 길이를 충족할 경우
            MAX = Math.max(MAX, len);
            if(size % 2 == 1) {  // 홀수일 경우
                binarySearch(len + 1, size + 1);  // 더 길게 자르기
            } else {  // 짝수일 경우
                binarySearch(len + (size / 2), size / 2);  // 더 길게 자르기
            }
        }
    }
}
