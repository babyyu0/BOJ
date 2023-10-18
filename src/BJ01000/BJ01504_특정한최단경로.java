package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ01504_특정한최단경로 {

    private static class Node {
        protected Node(int index, long depth) {
            this.index = index; this.depth = depth;
        }
        int index;
        long depth;
    }
    private static int N;
    private static final long MAX_VALUE = 200000000;
    private static PriorityQueue<Node> pq;
    private static long[][] edge;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>((n1, n2) -> {  // 우선순위 설정
            if(n1.depth < n2.depth) return -1;
            else if(n2.depth < n1.depth) return 1;
            else return 0;
        });

        N = Integer.parseInt(st.nextToken());  // 노드의 수
        long E = Long.parseLong(st.nextToken());  // 간선의 수
        edge = new long[N + 1][N + 1];  // 간선 저장을 위한 2차원 배열
//        for (int i = 1; i <= N; i++) {
//            Arrays.fill(edge[i], MAX_VALUE);
//        }

        int n1, n2;  // 임의의 노드 저장할 변수 선언
        for (long i = 0; i < E; i++) {  // 간선의 개수만큼 반복
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());  // 노드 1
            n2 = Integer.parseInt(st.nextToken());  // 노드 2
            edge[n1][n2] = Integer.parseInt(st.nextToken());  // 노드 간의 거리 저장
            edge[n2][n1] = edge[n1][n2];  // 양방향이므로 반대도 저장
        }

        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());  // 지나야하는 노드 1 번호
        int v = Integer.parseInt(st.nextToken());  // 지나야하는 노드 2 번호

        long min = MAX_VALUE;
        long fromStartToU = bfs(1, u, new long[N + 1], new boolean[N + 1]);
        long fromStartToV = bfs(1, v, new long[N + 1], new boolean[N + 1]);
        long fromUToV = bfs(u, v, new long[N + 1], new boolean[N + 1]);
        long fromUToEnd = bfs(u, N, new long[N + 1], new boolean[N + 1]);
        long fromVToEnd = bfs(v, N, new long[N + 1], new boolean[N + 1]);
        if(fromStartToU < MAX_VALUE && fromUToV < MAX_VALUE && fromVToEnd < MAX_VALUE) {
            min = Math.min(min, fromStartToU + fromUToV + fromVToEnd);
        }
        if(fromStartToV < MAX_VALUE && fromUToV < MAX_VALUE && fromUToEnd < MAX_VALUE) {
            min = Math.min(min, fromStartToV + fromUToV + fromUToEnd);
        }

        System.out.println((min >= MAX_VALUE)? -1 : min);

    }

    private static long bfs(int startNode, int endNode, long[] dist, boolean[] visited) {pq.clear();
        pq.add(new Node(startNode, 0));
        Arrays.fill(dist, MAX_VALUE);
        dist[startNode] = 0;

        long curDepth; Node curNode;
        while(!pq.isEmpty()) {
            curNode = pq.poll();
            visited[curNode.index] = true;  // 방문 처리
            for (int i = 1; i <= N; i++) {
                curDepth = curNode.depth + edge[curNode.index][i];  // 거리 더하기
                // System.out.println(curNode.index + "부터 " + edge[curNode.index][i] + "까지의 거리: " + curDepth);
                if(edge[curNode.index][i] == 0 || visited[i] || dist[i] < curDepth) continue;  // 방문했거나 못할 경우 다시 지나지 않기
                dist[i] = curDepth;
                pq.add(new Node(i, curDepth));
            }
        }

        // System.out.println(startNode + "부터 " + endNode + "까지의 거리: " + dist[endNode]);
        return dist[endNode];
    }
}