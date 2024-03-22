package BJ14000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BJ14499_주사위굴리기 {
    private static int x, y;
    private static final int[] dices = {0, 0, 0, 0, 0, 0};
    private static boolean flag;
    private static ArrayDeque<Integer> deque;
    private static int[][] map;
    private static final int[] dr = {0, 0, 0, -1, 1}, dc = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 지도 세로 크기
        int m = Integer.parseInt(st.nextToken());  // 지도 가로 크기
        map = new int[n][m];
        deque = new ArrayDeque<>();
        deque.add(0);
        deque.add(0);
        deque.add(0);
        deque.add(0);
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());  // 명령 개수

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int tmp, nr, nc;
        for (int i = 0; i < K; i++) {  // 이동 명령 // 1: 동, 2: 서, 3: 북, 4: 남
            tmp = Integer.parseInt(st.nextToken());
            nr = x + dr[tmp];
            nc = y + dc[tmp];

            if (0 <= nr && nr < n && 0 <= nc && nc < m) {
                x = nr;
                y = nc;
                rollDice(tmp);
                insertNum();
                tmp = deque.pollFirst();
                sb.append(deque.peekFirst()).append("\n");
                deque.addFirst(tmp);
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void rollDice(int dir) {
        if (dir == 1) {  // 동쪽 이동
            if (!flag) moveHorizen();
            deque.addFirst(deque.pollLast());
        } else if (dir == 2) {  // 서쪽 이동
            if (!flag) moveHorizen();
            deque.addLast(deque.pollFirst());
        } else if (dir == 3) {  // 북쪽 이동
            if (flag) moveVertical();
            deque.addLast(deque.pollFirst());
        } else if (dir == 4) {  // 남쪽 이동
            if (flag) moveVertical();
            deque.addFirst(deque.pollLast());
        }
    }
    private static void insertNum() {
        int tmp = deque.pollLast();  // 바닥 면의 숫자
        if (map[x][y] == 0) {  // 지도의 숫자가 0일 경우
            deque.addLast(tmp);  // 바닥 면의 숫자 그대로 두기
            map[x][y] = tmp;  // 지도에 주사위 바닥 숫자 삽입
        } else {  // 지도에 숫자가 존재할 경우
            deque.addLast(map[x][y]);  // 바닥에 지도 숫자 삽입
            map[x][y] = 0;  // 지도 수는 0으로 변경
        }
    }

    private static void moveHorizen() {  // 북/남에서 동/서로 굴리기
        dices[1] = deque.pollFirst();
        dices[0] = deque.pollFirst();
        dices[4] = deque.pollFirst();
        dices[5] = deque.pollFirst();

        deque.add(dices[3]);
        deque.add(dices[0]);
        deque.add(dices[2]);
        deque.add(dices[5]);

        flag = true;
    }

    private static void moveVertical() {  // 동/서에서 북/남으로 굴리기
        dices[3] = deque.pollFirst();
        dices[0] = deque.pollFirst();
        dices[2] = deque.pollFirst();
        dices[5] = deque.pollFirst();

        deque.add(dices[1]);
        deque.add(dices[0]);
        deque.add(dices[4]);
        deque.add(dices[5]);

        flag = false;
    }
}
