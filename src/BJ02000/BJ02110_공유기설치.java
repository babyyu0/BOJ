package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ02110_공유기설치 {
    private static int N, C;
    private static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int start = houses[0], end = houses[N - 1] + 1, mid;
        while(start < end) {
            mid = (start + end) / 2;  // 최소 거리 이분 탐색
            if(findMinDist(mid - houses[0]) < C) {  // 공유기 개수보다 작게 설치 되었을 때
                //System.out.printf("최소 거리가 %2d일 때, 공유기 설치 불가능...\n", mid - houses[0]);
                end = mid;
            } else {  // 같거나 많을 때
                //System.out.printf("최소 거리가 %2d일 때, 공유기 설치 가능!\n", mid - houses[0]);
                start = mid + 1;  // 거리 조금씩 넓혀가보기 (start = 마지막 공유기 설치 가능 거리)
            }
        }
        System.out.println(start - 1 - houses[0]);
    }

    private static long findMinDist(int dist) {  // 최소 거리 설정
        int count = 1;  // 첫 집 + 1
        int lastRout = houses[0];  // 첫집 선택
        for (int i = 1; i < N; i++) {
            if(dist <= houses[i] - lastRout) {  // 최소거리 이상일 때
                count++;
                lastRout = houses[i];
            }
        }

        //System.out.printf("최소 거리가 %2d일 때, 공유기 설치 가능 개수는 %2d개\n", dist, count);
        return count;
    }
}
