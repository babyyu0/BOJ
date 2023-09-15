package BJ18000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ18111_마인크래프트 {

    private static int N, M;  // 가로 너비, 세로 너비
    private static int[][] map;
    private static int[] min;
    private static long B;  // 가진 블록

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        min = new int[2];
        min[0] = Integer.MAX_VALUE;

        B = Long.parseLong(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 257; i++) {
            land(i, 0, B);
        }

        System.out.println(min[0] + " " + min[1]);
    }

    private static void land(int landHeight, int time, long block) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(landHeight == map[i][j]) continue;  // 땅 높이가 같을 경우
                else if(landHeight < map[i][j]) {  // 땅 높이가 높을 경우
                    // System.out.println((map[i][j] - landHeight) + "개의 블록 제거");
                    block += (map[i][j] - landHeight);
                    time += ((map[i][j] - landHeight) * 2);  // 블록 제거
                } else {  // 땅 높이가 낮을 경우
                    // System.out.println((landHeight - map[i][j]) + "개의 블록 추가");
                    block -= (landHeight - map[i][j]);
                    time += (landHeight - map[i][j]);  // 블록 추가
                }

                if(min[0] < time) return;
            }
        }
        if(block < 0) return;

        if(time < min[0] || (time == min[0] && min[1] < landHeight)) {  // 시간이 더 작을 경우
            min[0] = time; min[1] = landHeight;
        }
    }
}
