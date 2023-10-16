package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01167_트리의지름 {
    private static class Edge {
        protected Edge(int node, long dist) {
            this.node = node; this.dist = dist;
        }
        int node;
        long dist;

        @Override
        public String toString() {
            return "Edge{" +
                    "node=" + node +
                    ", dist=" + dist +
                    '}';
        }
    }
    private static int V;
    private static long maxDist;  // 정점 개수
    private static List<Edge>[] nodes;
    private static int selected;
    private static Queue<Edge> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        queue = new ArrayDeque<>();

        V = Integer.parseInt(br.readLine());
        nodes = new List[V + 1];

        int curNode, linkedNode;
        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());
            curNode = Integer.parseInt(st.nextToken());
            nodes[curNode] = new ArrayList<>();

            linkedNode = Integer.parseInt(st.nextToken());
            while(linkedNode != -1) {
                nodes[curNode].add(new Edge(linkedNode, Long.parseLong(st.nextToken())));  // 간선 삽입
                linkedNode = Integer.parseInt(st.nextToken());
            }
        }

        findMax(1, new boolean[V + 1]);
        // System.out.println();
        findMax(selected, new boolean[V + 1]);

        System.out.println(maxDist);
    }

    private static void findMax(int node, boolean[] visited) {
        Edge curNode, peekNode;
        long sumDist;

        queue.clear();
        queue.add(new Edge(node, 0));
        visited[node] = true;

        while(!queue.isEmpty()) {
            // System.out.println(queue);
            curNode = queue.poll();
            for (int i = 0; i < nodes[curNode.node].size(); i++) {
                peekNode = nodes[curNode.node].get(i);
                if(visited[peekNode.node]) continue;  // 방문한 경우 건너뛰기

                visited[peekNode.node] = true;  // 방문 처리
                sumDist = curNode.dist + peekNode.dist;
                queue.add(new Edge(peekNode.node, sumDist)); // 거리 계산

                if(maxDist < sumDist) {
                    maxDist = sumDist;
                    selected = peekNode.node;
                }
            }
        }
    }
}
