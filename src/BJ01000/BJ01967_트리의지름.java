package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ01967_트리의지름 {
    private static class Node {
        private Node(int index, int weight) {
            this.index = index; this.weight = weight;
        }
        int index, weight;
    }
    private static int N; // 노드의 개수
    private static List<Node>[] edge;  // 간선의 가중치 저장할 배열
    private static int[] dist;  // 경로 가중치 저장할 배열
    private static boolean[] visited;  // 방문 처리 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 노드의 개수 입력 받기

        if(N == 1) {
            System.out.println(0);
            System.exit(0);
        }

        edge = new ArrayList[N + 1];
        dist = new int[N + 1];  // 가중치 배열 초기화
        visited = new boolean[N + 1];  // 방문 배열 초기화

        int U, V, W;  // 노드1과 노드2, 가중치를 담아줄 임의의 변수 선언
        for (int i = 0; i < N - 1; i++) {  // 노드의 개수만큼 간선의 정보 입력 반복
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            if(edge[U] == null) edge[U] = new ArrayList<>();
            edge[U].add(new Node(V, W));  // 노드1 -> 노드2 가중치 초기화

            if(edge[V] == null) edge[V] = new ArrayList<>();
            edge[V].add(new Node(U, W));  // 노드2 -> 노드1 가중치 초기화
        }

        int farIndex = findFarNode(1);  // 1에서부터 가장 먼 노드 찾기
        farIndex = findFarNode(farIndex);  // 1에서 가장 먼 노드에서부터 가장 먼 노드 찾기
        System.out.println(dist[farIndex]);
    }

    private static int findFarNode(int startNode) {  // 가장 가중치가 높은 노드 찾기
        Queue<Integer> queue = new ArrayDeque<>();  // 최장거리를 계산할 큐
        queue.add(startNode);  // 시작 노드 삽입

        Arrays.fill(dist, 0);  // 거리 계산 배열 초기화
        Arrays.fill(visited, false);  // 방문 배열 초기화
        visited[startNode] = true;  // 시작 노드 방문 처리
        int curIndex, selected = startNode;  // 큐에서 빼낸 값을 저장한 임의의 변수, 최장경로의 index 담을 변수 선언
        while(!queue.isEmpty()) {
            curIndex = queue.poll();
            visited[curIndex] = true;
            for (int i = 0; i < edge[curIndex].size(); i++) {
                // 방문할 수 있고, 길이가 더 길 경우
                if(!visited[edge[curIndex].get(i).index] && dist[edge[curIndex].get(i).index] < dist[curIndex] + edge[curIndex].get(i).weight) {
                    dist[edge[curIndex].get(i).index] = dist[curIndex] + edge[curIndex].get(i).weight;  // 거리에 더 긴 값 삽입
                    queue.add(edge[curIndex].get(i).index);  // 큐에 넣기
                    if(dist[selected] < dist[edge[curIndex].get(i).index]) selected = edge[curIndex].get(i).index;  // 시작 노드에서 가장 거리가 먼 노드 삽입
                }
            }
            /* for (int i = 1; i <= N; i++) {
                if(!visited[i] && edge[curIndex][i] != 0 && dist[i] < dist[curIndex] + edge[curIndex][i]) {
                    dist[i] = dist[curIndex] + edge[curIndex][i];  // 거리에 더 긴 값 삽입
                    queue.add(i);  // 큐에 넣기
                    if(dist[selected] < dist[i]) selected = i;  // 시작 노드에서 가장 거리가 먼 노드 삽입
                }
            } */
        }

        // System.out.println(startNode + "의 dist: " + Arrays.toString(dist));
        // System.out.println(startNode + "에서의 최장 경로는 " + selected + " 인덱스까지의 "+ dist[selected]);
        return selected;
    }
}
