package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01011_FlyMeToTheAlphaCentauri {
    private static class Location {
        public Location(long loc, long count, long k) {
            this.loc = loc;
            this.count = count;
            this.k = k;
        }

        long loc, count, k;  // 이동 횟수, 위치, 직전 이동 광년

        @Override
        public String toString() {
            return "Location{" +
                    "위치=" + loc +
                    ", 이동 횟수=" + count +
                    ", 직전 이동 광년=" + k +
                    '}';
        }
    }
    private static long to, MIN_MOVE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());  // 테스트케이스 개수

        StringTokenizer st; long from;

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            from = Long.parseLong(st.nextToken());
            to = Long.parseLong(st.nextToken());

            sb.append(findMinDist(to - from)).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
        
    }

    private static long findMinDist(long dist) {
        if(dist <= 2) return dist;

        long root = (long) Math.sqrt(dist);
        if(root * root != dist) root++;

        //System.out.printf("(%d * %d + %d * %d) / 2 < %d ? %d : %d\n", root,root, root-1, root-1, dist, 2 * root - 1, 2 * root - 2);
        if((root * root + (root - 1) * (root - 1)) / 2 < dist) return 2 * root - 1;
        return 2 * root - 2;
    }
}
