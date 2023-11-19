package BJ16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ16953_AtoB {
    private static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Long, Long> map = new HashMap<>();
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.comparingLong(o -> map.getOrDefault(o, 100000L)));
        map.put(A, 0L);
        pq.add(A);

        long num, chnum;
        while(!pq.isEmpty()) {
            // System.out.println(pq);
            num = pq.poll();

            chnum = num * 2;
            // System.out.println("변경 값: " + chnum);
            // System.out.print((map.get(num) + 1) + "과 ");
            // System.out.println(map.getOrDefault(chnum, 100000L) + " 비교");
            if(chnum <= B && map.get(num) + 1 < map.getOrDefault(chnum, 100000L)) {
                map.put(chnum, map.get(num) + 1);
                pq.add(chnum);
            }

            chnum = Long.parseLong(num + "1");
            // System.out.println("변경 값: " + chnum);
            if(chnum <= B && map.get(num) + 1 < map.getOrDefault(chnum, 100000L)) {
                map.put(chnum, map.get(num) + 1);
                pq.add(chnum);
            }
        }

        System.out.println((map.get(B) == null || map.get(B) == 100000L) ? -1 : map.get(B) + 1);
    }
}
