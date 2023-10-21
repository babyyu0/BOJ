package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01916_최소비용구하기 {
    // A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화
    // 버스 비용은 0 <= x < 100000
    // 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
    private static int N, M;  // 노드 개수, 간선 개수
    private static int S, E;  // 출발 노드, 도착 노드
    private static int[][] edge;  // 간선 저장
    private static int INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 노드 개수 N
        M = Integer.parseInt(br.readLine());  // 간선 개수 M
        INF = 100000 * N;  // 무한대를 표현할 값 초기화
        edge = new int[N + 1][N + 1];  // 간선을 저장할 2차원 배열 초기화
        for (int i = 1; i <= N; i++) {  // 간선을 max value로 초기화
            Arrays.fill(edge[i], INF);
        }

        int u, v;
        for (int i = 0; i < M; i++) {  // 간선 삽입 (단방향 그래프)
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            edge[u][v] = Math.min(edge[u][v], Integer.parseInt(st.nextToken()));
            // edge[v][u] = edge[u][v];
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());  // 시작점 S
        E = Integer.parseInt(st.nextToken());  // 도착점 E

        System.out.println(dijkstra());  // 다익스트라 함수 호출
    }

    private static int dijkstra() {
        int[] dist = new int[N + 1];  // 각 노드별 최소 비용을 찾을 dist 배열
        Arrays.fill(dist, INF);  // 최대값으로 초기화
        dist[S] = 0;  // 시작점은 0으로 초기화

        // 최소비용을 찾을 우선순위 큐
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.depth));
        pq.add(new Node(S, 0));  // 시작점 pq에 넣기

        Node curNode; int curDepth;  // 임의의 값 저장해줄 변수 선언
        while(!pq.isEmpty()) {  // pq가 빌 때까지
            curNode = pq.poll();
            for (int i = 1; i <= N; i++) {  // 각 노드 방문
                curDepth = curNode.depth + edge[curNode.index][i];  // 현재 노드를 거쳐 i까지 가는 거리 구하기
                if(edge[curNode.index][i] == INF || dist[i] <= curDepth) continue;  // 방문할 수 없는 경우 넘기기
                dist[i] = curDepth;  // 최소 값 삽입
                pq.add(new Node(i, curDepth));  // queue에 삽입해 거치기
            }
        }

        return dist[E];  // 끝 점의 최소값 구하기
    }

    private static class Node {
        protected Node(int index, int depth) {
            this.index = index; this.depth = depth;
        }
        int index, depth;
    }
}
