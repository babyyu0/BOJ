package BJ12000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12100_2048 {
    private static class Block {
        public Block(long num, int count) {
            this.num = num;
            this.count = count;  // 몇 번째 이동에서 움직였는지 확인할 변수
        }

        long num;
        int count;

        public Block clone() {
            return new Block(this.num, this.count);
        }
    }

    private static int N;
    private static long MAX_NUM;
    private static Block[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // MAP의 크기
        map = new Block[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = new Block(Long.parseLong(st.nextToken()), -1);  // 이동 번호를 -1로 초기화
            }
        }

        start(map, 0);  // 게임 시작
        System.out.println(MAX_NUM);
    }

    private static void start(Block[][] map, int count) {
        if (5 <= count) {  // 게임 다섯 번 모두 진행 했을 시
            //printMap(map);
            return;
        }
        Block[][] tmpMap = new Block[N][N];

        copyMap(map, tmpMap);  // 맵 복사
        moveUp(tmpMap, count);  // 위로 움직여 보기
        start(tmpMap, count + 1);  // 다음 게임

        copyMap(map, tmpMap);  // 맵 복사
        moveDown(tmpMap, count);  // 아래로 움직여 보기
        start(tmpMap, count + 1);  // 다음 게임

        copyMap(map, tmpMap);  // 맵 복사
        moveLeft(tmpMap, count);  // 왼쪽으로 움직여 보기
        start(tmpMap, count + 1);  // 다음 게임

        copyMap(map, tmpMap);  // 맵 복사
        moveRight(tmpMap, count);  // 오른쪽으로 움직여 보기
        start(tmpMap, count + 1);  // 다음 게임
    }

    private static void moveUp(Block[][] map, int count) {
        int r;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].num != 0) {
                    r = i;
                    while (0 <= r - 1 && map[r - 1][j].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[r - 1][j].num = map[r][j].num;
                        map[r--][j].num = 0;
                    }
                    if (0 <= r - 1 && map[r - 1][j].num == map[r][j].num && map[r - 1][j].count != count) {  // 합치기
                        map[r - 1][j].num *= 2;
                        map[r - 1][j].count = count;
                        map[r--][j].num = 0;
                    }
                    while (0 <= r - 1 && map[r - 1][j].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[r - 1][j] = map[i][j].clone();
                        map[r--][j].num = 0;
                    }
                    MAX_NUM = Math.max(MAX_NUM, map[r][j].num);
                }
            }
        }
    }

    private static void moveDown(Block[][] map, int count) {
        int r;
        for (int i = N - 1; 0 <= i; i--) {
            for (int j = N - 1; 0 <= j; j--) {
                if (map[i][j].num != 0) {
                    r = i;
                    while (r + 1 < N && map[r + 1][j].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[r + 1][j].num = map[r][j].num;
                        map[r++][j].num = 0;
                    }
                    if (r + 1 < N && map[r + 1][j].num == map[r][j].num && map[r + 1][j].count != count) {  // 합치기
                        map[r + 1][j].num *= 2;
                        map[r + 1][j].count = count;
                        map[r++][j].num = 0;
                    }
                    while (r + 1 < N && map[r + 1][j].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[r + 1][j] = map[i][j].clone();
                        map[r++][j].num = 0;
                    }
                    MAX_NUM = Math.max(MAX_NUM, map[r][j].num);
                }
            }
        }
    }

    private static void moveLeft(Block[][] map, int count) {
        int c;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].num != 0) {
                    c = j;
                    while (0 <= c - 1 && map[i][c - 1].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[i][c - 1].num = map[i][c].num;
                        map[i][c--].num = 0;
                    }
                    if (0 <= c - 1 && map[i][c - 1].num == map[i][c].num && map[i][c - 1].count != count) {  // 합치기
                        map[i][c - 1].num *= 2;
                        map[i][c - 1].count = count;
                        map[i][c--].num = 0;
                    }
                    while (0 <= c - 1 && map[i][c - 1].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[i][c - 1] = map[i][j].clone();
                        map[i][c--].num = 0;
                    }
                    MAX_NUM = Math.max(MAX_NUM, map[i][c].num);
                }
            }
        }
    }

    private static void moveRight(Block[][] map, int count) {
        int c;
        for (int i = N - 1; 0 <= i; i--) {
            for (int j = N - 1; 0 <= j; j--) {
                if (map[i][j].num != 0) {
                    c = j;
                    while (c + 1 < N && map[i][c + 1].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[i][c + 1].num = map[i][c].num;
                        map[i][c++].num = 0;
                    }
                    if (c + 1 < N && map[i][c + 1].num == map[i][c].num && map[i][c + 1].count != count) {  // 합치기
                        map[i][c + 1].num *= 2;
                        map[i][c + 1].count = count;
                        map[i][c++].num = 0;
                    }
                    while (c + 1 < N && map[i][c + 1].num == 0) {  // 움직이려는 방향이 빈칸일 때까지
                        map[i][c + 1] = map[i][j].clone();
                        map[i][c++].num = 0;
                    }
                    MAX_NUM = Math.max(MAX_NUM, map[i][c].num);
                }
            }
        }
    }

    private static void copyMap(Block[][] map, Block[][] tmpMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmpMap[i][j] = map[i][j].clone();
            }
        }
    }
}
