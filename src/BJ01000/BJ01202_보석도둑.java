package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ01202_보석도둑 {
    private static class Jewel implements Comparable<Jewel> {
        private Jewel(int index, long M, long V) {
            this.index = index;
            this.M = M;
            this.V = V;
        }
        int index;
        long M, V;  // 최대 1000000 무게, 가격

        @Override
        public int compareTo(Jewel o) {
            if (this.M == o.M) {
                return Long.compare(o.V, this.V);
            }
            return Long.compare(this.M, o.M);
        }

        @Override
        public String toString() {
            return "Jewel{" +
                    "index=" + index +
                    ", M=" + M +
                    ", V=" + V +
                    '}';
        }
    }
    private static int N, K;  // 최대 300000개 보석 개수, 가방 개수 (각 가방에 담을 수 있는 최대 무게는 Ci), 가방에는 한개 보석만 넣을 수 있음
    private static Jewel[] jewels;
    private static long[] bags;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        bags = new long[K];

        for (int i = 0; i < N; i++) {  // 보석 담기
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(i, Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        Arrays.sort(jewels);

        
        for (int i = 0; i < K; i++) {  // 가방 크기 담기
            bags[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(bags);

        long sum = 0;
        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.V == o2.V) {
                return Long.compare(o2.M, o1.M);
            }
            return Long.compare(o2.V, o1.V);
        });
        for (int i = 0, j = 0; i < K; i++) {
            for (; j < N && jewels[j].M <= bags[i]; j++) {
                pq.add(jewels[j]);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll().V;
            }
        }

        System.out.println(sum);
    }
}
