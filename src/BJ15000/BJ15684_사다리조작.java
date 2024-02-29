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
        MIN_BRIDGE = 4;

        verticalLines = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            verticalLines[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        boolean flag = true;
        for (int i = 1; i <= N; i++) {
            if (!canStraight(i, i, 1)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println(0);
            return;

        }

        selectBridge(1, 1);
        System.out.println(MIN_BRIDGE == 4 ? -1 : MIN_BRIDGE);
    }

    private static int selectBridge(int count, int r) {
        boolean flag = true;
        for (int i = r; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (!verticalLines[i][j] && !verticalLines[i][j - 1] && !verticalLines[i][j + 1]) {
                    //System.out.printf("%d단계 (%d, %d) 체크...\n", count, i, j);
                    verticalLines[i][j] = true;
                    flag = true;

                    for (int k = 1; k <= N && flag; k++) {
                        if (!canStraight(k, k, 1)) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        //System.out.println("성공! ::: " + count);
                        //return count;
                        MIN_BRIDGE = Math.min(count, MIN_BRIDGE);
                    } else if (count < Math.min(3, MIN_BRIDGE)) {
                        selectBridge(count + 1, i);
                    }

                    verticalLines[i][j] = false;
                }
            }
        }

        return 4;
    }

    private static boolean canStraight(int startC, int curC, int curR) {
        if (H < curR) {
            return (startC == curC);
        }
        if (verticalLines[curR][curC]) {
            curC++;
        } else if (verticalLines[curR][curC - 1]) {
            curC--;
        }
        return canStraight(startC, curC, curR + 1);
    }
}
