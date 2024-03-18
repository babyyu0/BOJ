package BJ17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17135_캐슬디펜스 {
    private static int N, M, D, MAX_SUM;  // 행의 수 N, 열의 수 M, 공격 거리 제한 D
    private static int[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0, new int[3]);  // 궁수의 위치 선택
        System.out.println(MAX_SUM);
    }

    private static void combination(int start, int count, int[] archers) {
        if (count == 3) {  // 궁수의 위치가 선택되었을 때
            visited = new int[N][M];  // 방문 배열 초기화
            count = 0;
            for (int i = N - 1; 0 <= i; i--) {
                count += shoot(i, archers);  // 적이 다가오며 게임 진행
            }
            MAX_SUM = Math.max(MAX_SUM, count);
            return;
        }
        for (int i = start; i < M; i++) {
            archers[count] = i;
            combination(i + 1, count + 1, archers);
        }
    }

    private static int shoot(int r, int[] archers) {  // 해당 라운드에서 쏠 수 있는 적 count
        int nr, nc, count = 0;
        for (int i = 0; i < 3; i++) {  // 궁수 3명 탐색
            for (int j = 0; j < D; j++) {  // 발사 가능한 거리가 짧은 순부터 탐색
                for (int k = -j; k <= j; k++) {  // 왼쪽부터 탐색
                    nr = r - (j - Math.abs(k));  // 행 계산
                    nc = archers[i] + k;  // 열 계산

                    if (0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == 1
                            && (visited[nr][nc] == 0 || visited[nr][nc] == r + 2)) {  // 방문 가능할 경우 (동시 발사 고려)
                        count += (visited[nr][nc] == 0) ? 1 : 0;  // 해당 라운드에서 이미 count한 적인지 판단
                        visited[nr][nc] = r + 2;  // 방문 처리
                        j = D;  // for문 두개 break용
                        break;
                    }
                }
            }
        }
        return count;
    }
}
