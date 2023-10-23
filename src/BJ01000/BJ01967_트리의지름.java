package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ01967_트리의지름 {
    private static class Node {
        private Node(int S, int E) {
            this.S = S; this.E = E;
        }

        int S, E;  // 시작점과 끝점
    }
    private static int N; // 노드의 개수
    private static int[][] edge, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 노드의 개수 입력 받기
        edge = new int[N + 1][N + 1];

        int U, V;  // 노드1과 노드2를 담아줄 임의의 변수 선언
        for (int i = 0; i < N - 1; i++) {  // 노드의 개수만큼 간선의 정보 입력 반복
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            edge[U][V] = Integer.parseInt(st.nextToken());  // 노드1 -> 노드2 가중치 초기화
            edge[V][U] = edge[U][V];  // 노드2 -> 노드1 가중치 초기화
        }

//        System.out.println("=== 간선의 가중치 ===");
//        System.out.println(Arrays.deepToString(edge).replace("], ", "],\n"));

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        // 초기 변수 초기화
        int[] dist = new int[N + 1];  // 최장 경로를 구할 dist 배열
        boolean[] visited = new boolean[N + 1];  // 방문 처리 배열
        Queue<Integer> queue = new ArrayDeque<>();
        int max = 0;  // 최장 경로 저장할 변수

        int curIndex;  // 큐에서 빼낼 Node 삽입할 임의 변수 선언
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist, 0);  // 거리 초기화
            Arrays.fill(visited, false); // 방문 여부 초기화
            queue.add(i);  // 시작 노드 큐에 삽입
            visited[i] = true;  // 시작 노드 방문 처리
            
            while(!queue.isEmpty()) {  // 큐가 빌 때까지
                curIndex = queue.poll();
                for (int j = 1; j <= N; j++) {
                    // 해당 노드를 방문한 적 없고, 간선이 존재하고, 시작 노드에서 현재 노드까지의 거리보다 중간 노드를 거쳐 가는 것이 더 클 경우
                    if(!visited[j] && 0 < edge[curIndex][j] && dist[j] < dist[curIndex] + edge[curIndex][j]) {
                        dist[j] = dist[curIndex] + edge[curIndex][j];  // 최대값 삽입
                        visited[j] = true;
                        queue.add(j);  // 이동된 i 삽입
                    }
                }
            }
            max = Math.max(max, dist[dist.length - 1]);
        }

        return max;
    }
}
