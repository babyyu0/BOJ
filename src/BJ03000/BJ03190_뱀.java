package BJ03000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ03190_뱀 {

    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }

    private static int N, K, loc = 0;
    private static ArrayDeque<Pos> snakes;
    private static int[][] map;
    private static final int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};  // 오른쪽 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];

        snakes = new ArrayDeque<>();
        snakes.add(new Pos(1, 1)); // 꼬리 ~ 머리
        map[1][1] = 2;  // 2 = 뱀의 시작

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;  // 1 = 사과
        }

        int L = Integer.parseInt(br.readLine());  // 방향 변환 횟수

        int count = 0, moveCount;
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            moveCount = Integer.parseInt(st.nextToken());  // 초 받아오기
            count = move(count, moveCount);

            if (count != moveCount) {
                //System.out.println("뱀의 길이: " + snakes + ", 시간: " + count);
                //print();
                System.out.println(count + 1);
                return;
            }

            //System.out.println("뱀의 길이: " + snakes + ", 시간: " + count);
            //print();

            switch (st.nextToken()) {
                case "L":  // 왼쪽일 경우
                    loc--;
                    if(loc < 0) loc = 3;
                    break;
                case "D":  // 오른쪽일 경우
                    loc++;
                    loc %= 4;
                    break;
                default:
                    break;
            }
        }

        count = move(count, N * N);
        System.out.println(count + 1);
    }

    private static int move(int start, int end) {
        int mr, mc;
        while (start++ != end) {
            mr = snakes.peekLast().r + dr[loc];
            mc = snakes.peekLast().c + dc[loc];

            if (mr <= 0 || N < mr || mc <= 0 || N < mc || map[mr][mc] == 2) {  // 더이상 이동이 불가능할 경우
                return start - 1;  // 남은 시간 전송
            }

            snakes.addLast(new Pos(mr, mc));  // 머리 늘리기

            if (map[mr][mc] == 0) {  // 꼬리 줄이기
                map[snakes.peekFirst().r][snakes.peekFirst().c] = 0;
                snakes.pollFirst();
            }
            map[mr][mc] = 2;
        }
        return end;
    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
