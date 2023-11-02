package BJ13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13549_숨바꼭질3 {

    private static class Location implements Comparable<Location> {

        private Location(int index, int sec) {
            this.index = index;
            this.sec = sec;
        }

        @Override
        public int compareTo(Location o) {
            if (this.sec != o.sec) {
                return Integer.compare(this.sec, o.sec);
            }
            return Integer.compare(Math.abs(K - this.index), Math.abs(K - o.index));
        }

        @Override
        public String toString() {
            return "Location{" +
                    "index=" + index +
                    ", sec=" + sec +
                    '}';
        }

        int index, sec;
    }

    private static int N, K;
    private static final int DIV = 100000;
    private static int[] sec;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K <= N) {
            System.out.println(N - K);
            return;
        }
        if (K <= N * 2) {
            System.out.println(K - N);
            return;
        }

        sec = new int[DIV + 1];
        Arrays.fill(sec, DIV);
        sec[N] = 0;

        findFastest2();
        System.out.println(sec[K]);
    }

    private static void findFastest2() {
        PriorityQueue<Location> pq = new PriorityQueue<>();
        pq.add(new Location(N, 0));

        Location curLocation;
        int mvIndex, mvSec;
        while (!pq.isEmpty()) {
            System.out.println(pq);
            curLocation = pq.poll();
            // 두 배
            mvIndex = curLocation.index * 2;
            if (curLocation.index <= K && mvIndex <= DIV && curLocation.sec < sec[mvIndex]) {
                sec[mvIndex] = curLocation.sec;
                pq.add(new Location(mvIndex, curLocation.sec));
            }
            // 한번 이동
            mvSec = curLocation.sec + 1;
            mvIndex = curLocation.index - 1;
            if (0 <= mvIndex && mvSec < sec[mvIndex]) {
                sec[mvIndex] = mvSec;
                pq.add(new Location(mvIndex, mvSec));
            }
            mvIndex = curLocation.index + 1;
            if (mvIndex <= K && mvSec < sec[mvIndex]) {
                sec[mvIndex] = mvSec;
                pq.add(new Location(mvIndex, mvSec));
            }
        }
    }
}
