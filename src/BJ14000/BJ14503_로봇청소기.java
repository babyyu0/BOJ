package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503_로봇청소기 {
    // dir(바라보는 방향) : 0(북), 1(동), 2(남), 3(서)
    private static class Robot {
        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        int r, c, dir;
    }
    private static int N, M, CLEAN;
    private static Robot robot;
    private static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 로봇 위치 입력
        st = new StringTokenizer(br.readLine());
        robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // System.out.println("로봇청소기 시작...");
        map[robot.r][robot.c] = 2; CLEAN++;  // 첫 번째 칸은 반드시 빈 칸이므로 청소
        on();

        System.out.println(CLEAN);
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print((map[i][j]) ? "1 " : "0 ");
//            }
//            System.out.println();
//        }
    }

    private static void on() {
        int mr, mc, mdir;
        boolean flag;
        while(true) {
            flag = false;
            for (int i = 1; i <= 4; i++) {  // 반시계 방향으로 돌기
                mdir = (robot.dir - i) + (robot.dir < i ? 4 : 0);  // 방향 계산식
                mr = robot.r + dr[mdir];
                mc = robot.c + dc[mdir];
                if (0 <= mr && mr < N && 0 <= mc && mc < M && map[mr][mc] == 0) {  // 청소 안 한 칸이 존재할 경우
                    map[mr][mc] = 2; // 청소
                    CLEAN++;
                    robot = new Robot(mr, mc, mdir);
                    flag = true;  // 청소 완료 표시
                    // System.out.println("[" + mr + ", " + mc + "] 칸으로 전진");
                    break;
                }
            }

            if(!flag) {  // 안 한 칸이 존재하지 않을 경우
                mdir = robot.dir + 2 - (2 <= (robot.dir) ? 4 : 0);
                mr = robot.r + dr[mdir];
                mc = robot.c + dc[mdir];
                if(0 <= mr && mr < N && 0 <= mc && mc < M && map[mr][mc] != 1) {
                    // System.out.println("[" + mr + ", " + mc + "] 칸으로 후진");
                    robot = new Robot(mr, mc, robot.dir);  // 후진
                } else {  // 벽에 막혔을 경우
//                    System.out.println("로봇청소기 종료...");
                    break;
                }
            }
        }
    }
}
