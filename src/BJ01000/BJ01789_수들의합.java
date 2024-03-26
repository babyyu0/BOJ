package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class BJ01789_수들의합 {
    private static long num;
    private static PriorityQueue<Long> pq;
    private static Set<Long> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pq = new PriorityQueue<>(Collections.reverseOrder());
        set = new HashSet<>();
        num = Long.parseLong(br.readLine());
        pq.add(num);
        set.add(num);
        findMaxSum();
        System.out.println(set.size());
    }

    private static void findMaxSum() {
        long num, i = 1;

        while (!pq.isEmpty()) {
            num = pq.poll();
            while (set.contains(i)) i++;
            if (0 < num - i && !set.contains(num - i) && num - i != i) {
                set.add(num - i);
                set.add(i);
                pq.add(num - i);
                pq.add(i++);
                set.remove(num);
            }
        }
    }
}
