package BJ15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ15683_감시 {
    private static class CCTV {
        public CCTV(int num, int r, int c) {
            this.num = num;
            this.r = r;
            this.c = c;
        }

        int num, r, c;
    }
    private static int N, M;
    private static int[][] map;
    private static PriorityQueue<CCTV> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 세로 크기
        M = Integer.parseInt(st.nextToken());  // 가로 크기
        map = new int[N][M];
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.num));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(1 <= map[i][j] && map[i][j] < 6) {  // CCTV일 경우
                    pq.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        // CCTV 감시 확인하기
        CCTV cctv;
        while(!pq.isEmpty()) {
            cctv = pq.poll();
            findMaxCheck(cctv);
        }
    }

    private static void findMaxCheck(CCTV cctv) {
        int[] canCheck = new int[4];  // 동 서 남 북
        for (int i = cctv.c; i < M; i++) {  // 동 확인
            if(map[cctv.r][i] == 6) break;
            if(map[cctv.r][i] == 0) canCheck[0]++;
        }
        for (int i = cctv.c; 0 <= i; i--) {  // 서 확인
            if(map[cctv.r][i] == 6) break;
            if(map[cctv.r][i] == 0) canCheck[1]++;
        }
        for (int i = cctv.r; i < N; i++) {  // 북 확인
            if(map[i][cctv.c] == 6) break;
            if(map[i][cctv.c] == 0) canCheck[2]++;
        }
        for (int i = cctv.r; 0 <= i; i--) {  // 북 확인
            if(map[i][cctv.c] == 6) break;
            if(map[i][cctv.c] == 0) canCheck[3]++;
        }
        System.out.printf("(%d, %d) 위치의 %d번 CCTV의 동서남북 확인 범위는 %s", cctv.r, cctv.c, cctv.num, Arrays.toString(canCheck));
    }
}
