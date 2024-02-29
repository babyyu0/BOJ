package BJ15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15684_사다리조작 {
    private static int N, M, H, MIN_BRIDGE;  // 사다리 세로선, 가로선, 가로선 가능 개수
    private static boolean[][] verticalLines; // 가로선 존재 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        MIN_BRIDGE = 4;  // INF 값 정의

        verticalLines = new boolean[H + 1][N + 1];  // 가로선 위치 표기할 배열
        for (int i = 0; i < M; i++) {  // 주어진 가로선 위치 표기
            st = new StringTokenizer(br.readLine());
            verticalLines[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        boolean flag = true;  // 사다리를 탔을 때 i번 출발점이 i번 도착점을 가리키는지
        for (int i = 1; i <= N; i++) {  // 가로선을 하나도 놓지 않았을 때
            if (!canStraight(i, i, 1)) {  // 가리키지 않는 경우
                flag = false;  // false 설정
                break;
            }
        }
        if (flag) {  // (1-n) 출발점이 모두 (1-n)번 도착점을 가리킬 경우 (false에 걸리지 않았을 경우)
            System.out.println(0);  // 가로선을 하나도 안 놓아도 됨
            return;

        }

        selectBridge(1, 1);  // 1개부터 3개까지의 가로선 선택 (완전 탐색)
        System.out.println(MIN_BRIDGE == 4 ? -1 : MIN_BRIDGE);
    }

    private static void selectBridge(int count, int r) {
        boolean flag;  // 사다리를 탔을 때 i번 출발점이 i번 도착점을 가리키는지를 나타낼 flag 변수 선언
        for (int i = r; i <= H; i++) {  // 가로선 탐색
            for (int j = 1; j < N; j++) {  // 세로선 탐색
                if (!verticalLines[i][j] && !verticalLines[i][j - 1] && !verticalLines[i][j + 1]) {  // 가로선을 놓을 수 있는 경우
                    //System.out.printf("%d단계 (%d, %d) 체크...\n", count, i, j);
                    verticalLines[i][j] = true;  // 가로선 놓기
                    flag = true;  // flag를 true로 초기화

                    for (int k = 1; k <= N && flag; k++) {
                        if (!canStraight(k, k, 1)) {  // 가리키지 않는 경우
                            flag = false;  // false 설정
                            break;
                        }
                    }

                    if (flag) {  // (1-n) 출발점이 모두 (1-n)번 도착점을 가리킬 경우 (false에 걸리지 않았을 경우)
                        //System.out.println("성공! ::: " + count);
                        //return count;  // 더 작게 놓을 수 있는 경우가 있을 수 있으므로 바로 반환해선 안됨!
                        MIN_BRIDGE = Math.min(count, MIN_BRIDGE);  // 더 작은 값으로 최소 가로선 설정
                    } else if (count < Math.min(3, MIN_BRIDGE)) {  // 가로선이 3개 미만이거나, 최소 가로선 미만일 경우
                        selectBridge(count + 1, i);  // 가로선 하나 더 놓기 (재귀)
                    }

                    verticalLines[i][j] = false;  // 볼장 다 보면 방문 해제
                }
            }
        }
    }

    private static boolean canStraight(int startC, int curC, int curR) {  // i번 출발점이 i번 도착점을 가리키는지 검사하는 재귀 함수
        if (H < curR) {  // 가로선을 다 타고 내려왔을 때
            return (startC == curC);  // 출발점과 도착점이 같은지에 대한 boolean 반환
        }
        if (verticalLines[curR][curC]) {  // 오른쪽으로 가는 가로선이 존재할 경우
            curC++;  // 한 칸 오른쪽으로 이동
        } else if (verticalLines[curR][curC - 1]) {  // 왼쪽으로 가는 가로선이 존재할 경우
            curC--;  // 한 칸 왼쪽으로 이동
        }
        return canStraight(startC, curC, curR + 1);  // 다음 칸 탐색
    }
}
