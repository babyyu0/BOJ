package BJ13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13549_숨바꼭질3 {
    private static int N, K;
    private static final int DIV = 100000;
    private static int[] sec;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sec = new int[DIV + 1];
        Arrays.fill(sec, DIV);
        sec[N] = 0;

        findFastest();
        System.out.println(sec[K]);
    }

    private static void findFastest() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> sec[o]));
        pq.add(N);  // 시작점 N 삽입
        sec[N] = 0;  // N의 거리 초기화

        int curIndex;
        while(!pq.isEmpty()) {
            // System.out.println(pq);
            curIndex = pq.poll();

            if(curIndex * 2 <= DIV && curIndex * 2 <= K + N && sec[curIndex] < sec[curIndex * 2]) {
                sec[curIndex * 2] = sec[curIndex];
                pq.add(curIndex * 2);
            }
            if(curIndex + 1 <= K && sec[curIndex] < sec[curIndex + 1]) {
                sec[curIndex + 1] = sec[curIndex] + 1;
                pq.add(curIndex + 1);
            }

            if (0 <= curIndex - 1 && sec[curIndex] + 1 < sec[curIndex - 1]) {
                sec[curIndex - 1] = sec[curIndex] + 1;
                pq.add(curIndex - 1);
            }
        }

        /*
        System.out.println("{");
        for (int i = 0; i <= K + N; i++) {
            System.out.println("\t" + i + ": " + sec[i] + "초");
        }
        System.out.println("}");
         */

    }
}
