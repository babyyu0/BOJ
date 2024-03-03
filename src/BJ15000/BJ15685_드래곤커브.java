package BJ15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15685_드래곤커브 {
    private static int N;  // 드래곤 커브의 개수 (1 <= N <= 20)
    private static boolean[][] dragonCurve;  // 방문 check
    private static List<Integer> dirList;  // 이동 방향 저장할 List
    private static final int[] dr = {0, -1, 0, 1}, dc = {1, 0, -1, 0};  // 방향 구분

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 드래곤 커브 개수 받기
        dragonCurve = new boolean[101][101];  // 최대 Map 크기 초기화
        dirList = new ArrayList<>();  // 이동 방향 저장할 List 초기화

        StringTokenizer st;
        int x, y, d, g, nr, nc;
        for (int i = 1; i <= N; i++) {
            dirList.clear();  // 리스트 비우기 (이동 방향 0 스택)
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            // 0단계 드래곤 포스
            int[] lastPos = new int[]{y + dr[d], x + dc[d]};  // [0]: row(세로) 이동, [1]: col(가로) 이동
            //System.out.printf("(%d, %d)에서 (%d, %d)로 이동\n", y, x, nr, nc);
            //System.out.printf("0단계 완료\n\n");

            dragonCurve[y][x] = dragonCurve[lastPos[0]][lastPos[1]] = true;  // 0단계 드래곤 포스 방문 체크
            dirList.add(d);  // 방향 삽입
            for (int j = 0; j < g; j++) {  // 드래곤커브 단계
                // j단계 드래곤 커브 시작
                lastPos = makeDragonCurve(lastPos[0], lastPos[1], dirList.size());  // 드래곤 커브 함수 호출
                //printDragonCurve();
                //System.out.printf("%d단계 완료\n\n", j + 1);
            }
        }
        System.out.println(countDragonCurve());  // 사각형 개수 세기
    }

    private static int[] makeDragonCurve(int r, int c, int count) {
        if (count == 0) return new int[]{r, c};  // 드래곤 커브 끝점 반환

        int nd = (dirList.get(count - 1) + 1) % 4;  // 드래곤 커브 진행 방향 계산
        int nr = r + dr[nd];
        int nc = c + dc[nd];

        dirList.add(nd);  // 진행 방향을 방향 List에 삽입
        dragonCurve[nr][nc] = true;  // 방문 체크
        //System.out.printf("%3d: (%d, %d)에서 (%d, %d)로 이동\n", count, r, c, nr, nc);
        return makeDragonCurve(nr, nc, count - 1);  // 이전 드래곤 커브에 따른 다음 드래곤 커브 선 그리기
    }

    private static int countDragonCurve() {
        int count = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                // 방문한 점으로 사각형을 만들 수 있을 경우
                if (dragonCurve[i][j] && dragonCurve[i - 1][j] && dragonCurve[i][j - 1] && dragonCurve[i - 1][j - 1]) {
                    count++;  // count + 1
                }
            }
        }

        return count;
    }

    private static void printDragonCurve() {
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.printf("%1s ", dragonCurve[i][j] ? "T" : "F");
            }
            System.out.println();
        }
        System.out.println("=====================");
    }
}
