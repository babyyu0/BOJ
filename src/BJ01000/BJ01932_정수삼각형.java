package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ01932_정수삼각형 {

    private static int N;
    private static long[][] triangle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        triangle = new long[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(triangle[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp();
        long max = -1;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, triangle[N - 1][i]);
        }

        System.out.println(max);
    }

    private static void dp() {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if(j - 1 < 0) {
                    triangle[i][j] += triangle[i - 1][j];
                } else {
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
                }
            }
        }
    }

    /*
    private static long dijkstra() {
        long[] dist = new long[N];
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            queue.add(new Node(N - 1, i, triangle[N - 1][i]));
            dist[N - 1] = Math.max(dist[N - 1], triangle[N - 1][i]);
        }

        Node curNode; long curSum;
        while(!queue.isEmpty()) {
            curNode = queue.poll();
            if(curNode.r - 1 < 0) continue;
            // 대각선 오른쪽이 선택했을 때
            if(0 <= curNode.c - 1) {
                curSum = curNode.sum + triangle[curNode.r - 1][curNode.c - 1];
                if(0 <= triangle[curNode.r - 1][curNode.c - 1] && dist[curNode.r - 1] <= curSum) {
                    dist[curNode.r - 1] = curSum;
                    queue.add(new Node(curNode.r - 1, curNode.c -1, curSum));
                }
            }
            // 대각선 왼쪽이 선택했을 때
            curSum = curNode.sum + triangle[curNode.r - 1][curNode.c];
            if(0 <= triangle[curNode.r - 1][curNode.c] && dist[curNode.r] <= curSum) {
                dist[curNode.r - 1] = curSum;
                queue.add(new Node(curNode.r - 1, curNode.c, curSum));
            }
        }

        return dist[0];
    }
     */
}
