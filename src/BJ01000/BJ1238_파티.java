package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1238_파티 {
    private static class Node {
        protected Node(int index, int depth) {
            this.index = index; this.depth = depth;
        }
        int index, depth;

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", depth=" + depth +
                    '}';
        }
    }

    private static int N, M, X, max; // 노드, 간선, 최종 노드
    private static int[][] edge;  // 간선 표시
    // private static boolean[][] visited;
    private static int[] visited, visited2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        edge = new int[N + 1][N + 1];
        // visited = new boolean[N + 1][N + 1];
        visited = new int[N + 1];
        visited2 = new int[N + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Arrays.fill(visited2, Integer.MAX_VALUE);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edge[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        bfs(); reverseBfs();
        // System.out.println(Arrays.toString(visited));
        // System.out.println(Arrays.toString(visited2));
        visited[X] = 0; visited2[X] = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, visited[i] + visited2[i]);
        }
        System.out.println(max);
    }

    private static void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(X, 0));

        Node curNode;
        int sum;
        while(!queue.isEmpty()) {
            // System.out.println(queue);
            curNode = queue.poll();
            for (int i = 1; i <= N; i++) {
                sum = curNode.depth + edge[i][curNode.index];
                // if(visited[i][curNode.index] || edge[i][curNode.index] == 0) continue;
                if(visited[i] < sum || edge[i][curNode.index] == 0) continue;

                // visited[i][curNode.index] = true;  // 방문 처리
                visited[i] = sum;  // 방문 처리
                queue.add(new Node(i, sum));
            }
        }
    }

    private static void reverseBfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(X, 0));

        Node curNode;
        int sum;
        while(!queue.isEmpty()) {
            // System.out.println(queue);
            curNode = queue.poll();
            for (int i = 1; i <= N; i++) {
                sum = curNode.depth + edge[curNode.index][i];
                if(visited2[i] < sum || edge[curNode.index][i] == 0) continue;

                // visited[curNode.index][i] = true;  // 방문 처리
                visited2[i] = sum;  // 방문 처리
                queue.add(new Node(i, sum));
            }
        }
    }
}
