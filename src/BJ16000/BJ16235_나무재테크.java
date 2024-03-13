package BJ16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16235_나무재테크 {
    private static int N, M, K;  // N: 땅 크기, K: 지난 년도
    private static int[][] A;  // 양분 추가 개수
    private static int[][] map;
    private static List<Integer>[][] trees;
    private static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());  // 나무 개수
        K = Integer.parseInt(st.nextToken());
        A = new int[N + 1][N + 1];
        map = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;  // 양분 초기화
            }
        }

        trees = new ArrayList[N + 1][N + 1];
        int x, y, z;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            if(trees[x][y] == null) trees[x][y] = new ArrayList<>();
            trees[x][y].add(z);
        }

        for (int i = 0; i < K; i++) {
            growTrees();  // 봄에 나무가 자신의 나이만큼 양분을 먹음 + 죽은 나무는 양분행 + 비료 뿌리기
            if(M == 0) break;
            breedTrees();  // 가을에 나무 번식
        }
        System.out.println(M);
    }
    private static void growTrees() {
        int index;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(trees[i][j] != null) {
                    index = 0;
                    while (index < trees[i][j].size() && 0 <= map[i][j] - trees[i][j].get(index)) {
                        map[i][j] -= trees[i][j].get(index);
                        trees[i][j].set(index, trees[i][j].get(index++) + 1);
                    }
                    while (!trees[i][j].isEmpty() && index < trees[i][j].size()) {
                        map[i][j] += trees[i][j].remove(index) / 2;
                        M--;
                    }
                }
                map[i][j] += A[i][j];  // 양분 주기
            }
        }
    }

    private static void breedTrees() {
        int nr, nc;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(trees[i][j] == null) continue;
                for (int tree : trees[i][j]) {
                    if(tree % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            nr = i + dr[k];
                            nc = j + dc[k];
                            if(0 < nr && nr <= N && 0 < nc && nc <= N) {
                                if(trees[nr][nc] == null) trees[nr][nc] = new ArrayList<>();
                                trees[nr][nc].add(0, 1);
                                M++;
                            }
                        }
                    }
                }
            }
        }
    }
}