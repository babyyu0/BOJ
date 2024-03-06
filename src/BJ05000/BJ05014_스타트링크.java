package BJ05000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ05014_스타트링크 {
    private static int F, U, D;
    private static int[] clicked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());  // 층 개수
        int S = Integer.parseInt(st.nextToken());  // 지금 있는 곳
        int G = Integer.parseInt(st.nextToken());  // 이동할 위치
        U = Integer.parseInt(st.nextToken());  // U층 위
        D = Integer.parseInt(st.nextToken());  // D층 아래
        clicked = new int[F + 1];
        int INF = F * 2 + 2;
        Arrays.fill(clicked, INF);
        clicked[S] = 0;

        findButtonClick(S);
        //System.out.println(Arrays.toString(clicked));
        System.out.println((clicked[G] < INF) ? clicked[G] : "use the stairs");
    }

    private static void findButtonClick(int curFloor) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(curFloor);

        int up, down;
        while(!queue.isEmpty()) {
            //System.out.println(queue);
            curFloor = queue.poll();
            up = curFloor + U;
            down = curFloor - D;

            if (up <= F && clicked[curFloor] + 1 < clicked[up]) {
                clicked[up] = clicked[curFloor] + 1;
                queue.add(up);
            }
            if (0 < down && clicked[curFloor] + 1 < clicked[down]) {
                clicked[down] = clicked[curFloor] + 1;
                queue.add(down);
            }
        }
    }
}
