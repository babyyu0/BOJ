package BJ16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ16234_인구이동 {
    private static class Pos {
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        int r, c;
    }
    // 각 땅의 나라인 r행 c열에는 A[r][c]명이 살고있음
    // 인접한 나라 사이 국경선 존재, 모든 국경선은 정사각형

    private static int N, L, R;
    private static int[][] map, tmpMap;
    private static Queue<Pos> queue;
    private static List<Pos> worldList;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        // while 인구 이동이 없을 때까지
        // 1. 국경선을 공유하는 두 나라의 인구차이가 L명 이상, R명 이하라면 두 나라가 공유하는 국경선을 하루동안 오픈
        // 2. 모든 국경선이 열렸을 시 인구이동 시작
        // 3. 인접한 칸만을 이용해 국경선 이동 가능 시 하루동안 그 나라는 연합
        // 4. 연합을 이루고 있는 각 칸의 인구 수는 (연합의 인구 수) / (연합을 이루는 칸의 개수), 소수점 버리기
        // 5. 연합 해체후 국경선 닫기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 땅 크기
        L = Integer.parseInt(st.nextToken());  // L 이상
        R = Integer.parseInt(st.nextToken());  // R 이하
        map = new int[N][N];
        queue = new ArrayDeque<>();
        worldList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean flag;
        int day = 0;
        do {
            day++;
            flag = false;
            tmpMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (0 < tmpMap[i][j]) continue;
                    if (findLine(i, j)) {
                        flag = true;
                    }
                }
            }
            map = tmpMap.clone();
        } while (flag);

        System.out.println(day - 1);
    }

    private static boolean findLine(int r, int c) {
        queue.add(new Pos(r, c));
        tmpMap[r][c] = map[r][c];
        worldList.clear();
        worldList.add(new Pos(r, c));

        int people = map[r][c];
        Pos pos;
        int nr, nc, diff;
        while (!queue.isEmpty()) {
            pos = queue.poll();
            for (int i = 0; i < 4; i++) {
                nr = pos.r + dr[i];
                nc = pos.c + dc[i];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && tmpMap[nr][nc] == 0) {  // 방문가능할 경우
                    diff = Math.abs(map[nr][nc] - map[pos.r][pos.c]);
                    if (L <= diff && diff <= R) {  // 차이가 L 이상 R 이하일 때
                        tmpMap[nr][nc] = map[nr][nc];
                        worldList.add(new Pos(nr, nc));
                        queue.add(new Pos(nr, nc));
                        people += map[nr][nc];
                    }
                }
            }
        }

        people /= worldList.size();
        boolean flag = false;
        for (Pos world : worldList) {
            if (map[world.r][world.c] != people) {
                flag = true;
            }
            tmpMap[world.r][world.c] = people;
        }

        return flag;  // 이동을 했을 경우 true
    }
}