package BJ16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ16235_나무재테크 {
    private static int N, M, K;  // N: 땅 크기, M: 나무 개수, K: 지낼 년도
    private static int[][] A, map;  // 양분 추가 개수, 현재 양분
    private static List<Integer>[][] trees;  // 나무 배열
    private static final int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1}, dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 땅 크기 초기화
        M = Integer.parseInt(st.nextToken());  // 나무 개수 초기화
        K = Integer.parseInt(st.nextToken());  // 지낼 년도 초기화
        A = new int[N + 1][N + 1];  // 추가 양분 담을 배열 초기화
        map = new int[N + 1][N + 1];  // 현재 양분 담을 배열 초기화

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());  // 각 땅의 양분 개수 초기화
                map[i][j] = 5;  // 각 땅의 현재 양분 초기화
            }
        }

        trees = new ArrayList[N + 1][N + 1];  // 나무 저장할 List 2차원 배열 초기화
        int x, y, z;  // 임의 변수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());  // 세로
            y = Integer.parseInt(st.nextToken());  // 가로
            z = Integer.parseInt(st.nextToken());  // 나무 나이

            if (trees[x][y] == null) trees[x][y] = new ArrayList<>();  // 배열 null일 시 새로 만들어주기
            trees[x][y].add(z);  // 배열에 나무 나이 담기
        }

        for (int i = 0; i < K; i++) {
            growTrees();  // 나무가 자신의 나이만큼 양분을 먹음 + 죽은 나무는 양분행 + 양분 뿌리기 (봄, 여름, 겨울)
            if (M == 0) break;  // 나무가 없을 경우 더이상 번식하지 못하므로 break
            breedTrees();  // 나무 번식 (가을)
        }
        System.out.println(M);  // 나무 개수 출력
    }

    private static void growTrees() {
        int index;
        for (int i = 1; i <= N; i++) {  // 세로
            for (int j = 1; j <= N; j++) {  //가로
                if (trees[i][j] != null) {  // 해당 칸에 나무가 존재할 경우
                    index = 0;  // index 0부터 시작
                    while (index < trees[i][j].size() && 0 <= map[i][j] - trees[i][j].get(index)) {  // 양분을 줄 수 있을 때까지 나무 키우기
                        map[i][j] -= trees[i][j].get(index);  // 양분 먹기 (빼기)
                        trees[i][j].set(index, trees[i][j].get(index++) + 1);  // 나무 나이 + 1
                    }
                    while (!trees[i][j].isEmpty() && index < trees[i][j].size()) {  // 양분을 먹을 수 없는 나무들 처리
                        map[i][j] += trees[i][j].remove(index) / 2;  // 나이의 반만큼 양분 더하기
                        M--;  // 나무 개수 빼기
                    }
                }
                map[i][j] += A[i][j];  // 기계로 양분 주기
            }
        }
    }

    private static void breedTrees() {
        int nr, nc;
        for (int i = 1; i <= N; i++) {  // 세로
            for (int j = 1; j <= N; j++) {  // 가로
                if (trees[i][j] == null) continue;  // 해당 칸에 나무가 존재하지 않을 경우 넘기기
                for (int tree : trees[i][j]) {  // 존재하는 나무 반복
                    if (tree % 5 == 0) {  // 나무의 나이가 5의 배수일 경우
                        for (int k = 0; k < 8; k++) {  // 팔방 탐색
                            nr = i + dr[k];
                            nc = j + dc[k];
                            if (0 < nr && nr <= N && 0 < nc && nc <= N) {  // 해당 칸에 나무를 심을 수 있을 경우
                                if (trees[nr][nc] == null) trees[nr][nc] = new ArrayList<>();  // 배열 null일 시 새로 만들어주기
                                trees[nr][nc].add(0, 1);  // 가장 앞에 나이 1 나무 심기
                                M++;  // 나무 개수 더하기
                            }
                        }
                    }
                }
            }
        }
    }
}