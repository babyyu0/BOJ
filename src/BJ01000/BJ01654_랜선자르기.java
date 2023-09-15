package BJ01000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ01654_랜선자르기 {

    private static int K;  // 이미 가지고 있는 랜선 개수
    private static int N;  // 필요 랜선 개수
    private static int cutCnt;
    private static long MAX;
    private static long[] lans;  // 랜선 길이 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        lans = new long[K];

        long maxLan = 0;
        for (int i = 0; i < K; i++) {
            lans[i] = Long.parseLong(br.readLine());  // 랜선 길이 입력받기
            maxLan = Math.max(maxLan, lans[i]);
        }

        cutLans(maxLan, maxLan);

        sb.append(MAX);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void cutLans(long cutSize, long size) {
        // System.out.println("cutSize : " + cutSize + ", size : " + size);
        cutCnt = 0;
        for (int i = 0; i < K; i++) {
            cutCnt += lans[i] / cutSize;
        }
        // System.out.println("cutCnt : " + cutCnt);
        // System.out.println();

        if(size == 1) {
            if(N <= cutCnt) MAX = Math.max(MAX, cutSize);
            return;
        } else if(cutCnt < N) {  // 잘린 개수가 잘라야하는 수보다 작은 경우
            if(size % 2 == 1) cutLans(cutSize - 1, size - 1);
            else cutLans(cutSize - (size / 2), size / 2);
        } else {
            MAX = Math.max(MAX, cutSize);
            if(size % 2 == 1) cutLans(cutSize + 1, size + 1);
            else cutLans(cutSize + (size / 2), size / 2);
        }
    }

}
