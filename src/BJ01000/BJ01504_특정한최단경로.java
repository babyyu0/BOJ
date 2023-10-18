package BJ01000;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ01504_특정한최단경로 {

    private static class Node {
        protected Node(int index, int depth) {
            this.index = index; this.depth = depth;
        }
        int index, depth;
    }
    private static int N;
    private static PriorityQueue<Node> queue;
    private static int[][] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new PriorityQueue<>((n1, n2) -> {
            if(n1.depth < n2.depth) return -1;
            else if(n2.depth < n1.depth) return 1;
            else return 0;
        });

        N = Integer.parseInt(st.nextToken());  // 노드의 수
        int E = Integer.parseInt(st.nextToken());  // 간선의 수
        edge = new int[N + 1][N + 1];  // 간선 저장을 위한 2차원 배열

        int n1, n2;  // 임의의 노드 저장할 변수 선언
        for (int i = 0; i < E; i++) {  // 간선의 개수만큼 반복
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());  // 노드 1
            n2 = Integer.parseInt(st.nextToken());  // 노드 2
            
            edge[n1][n2] = Integer.parseInt(st.nextToken());  // 노드 간의 거리 저장
            edge[n2][n1] = edge[n1][n2];  // 양방향이므로 반대도 저장
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());  // 지나야하는 노드 1 번호
        int v = Integer.parseInt(st.nextToken());  // 지나야하는 노드 2 번호

        int fromStartToU = bfs(1, u, new boolean[N + 1], Integer.MAX_VALUE);
        int fromStartToV = bfs(1, v, new boolean[N + 1], Integer.MAX_VALUE);
        int fromUToV = bfs(u, v, new boolean[N + 1], Integer.MAX_VALUE);
        int fromUToEnd = bfs(u, N, new boolean[N + 1], Integer.MAX_VALUE);
        int fromVToEnd = bfs(v, N, new boolean[N + 1], Integer.MAX_VALUE);

        int U = Integer.MAX_VALUE, V = Integer.MAX_VALUE;
        if(fromStartToU < fromStartToV) {
            U = Math.min(fromUToV + fromUToEnd, (fromUToV * 2) + fromVToEnd);
        }
        else {
            V = Math.min(fromUToV + fromVToEnd, (fromUToV * 2) + fromUToEnd);
        }

        System.out.println(Math.min(U, V) == Integer.MAX_VALUE? 0 : Math.min(U, V));

    }

    private static int bfs(int startNode, int endNode, boolean[] visited, int min) {
        queue.clear();
        queue.add(new Node(startNode, 0));
        visited[startNode] = true;

        int curDepth; Node curNode;
        while(!queue.isEmpty()) {
            curNode = queue.poll();
            for (int i = 1; i <= N; i++) {
                curDepth = curNode.depth + edge[curNode.index][i];
                if(visited[i]) continue;  // 방문한 경우 다시 지나지 않기
                if(i == endNode) {  // 끝점일 경우
                    min = Math.min(min, curDepth);  // 값 비교
                    continue;
                }
                visited[i] = true;  // 방문 처리
                queue.add(new Node(i, curDepth));
            }
        }

        // System.out.println(startNode + "부터 " + endNode + "까지의 최단 경로 : " + min);
        return min;
    }
}
