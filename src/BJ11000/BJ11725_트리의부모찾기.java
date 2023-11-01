package BJ11000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11725_트리의부모찾기 {

    private static int N;
    private static int[] parent;
    private static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());  // 노드의 수
        parent = new int[N + 1];  // 부모 노드를 담을 배열 초기화
        nodes = new ArrayList[N + 1];  // 간선을 담을 List 배열 초기화

        int u, v;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());  // 노드 1 인덱스 입력
            v = Integer.parseInt(st.nextToken());  // 노드 2 인덱스 입력

            if (nodes[u] == null) {  // u 인덱스의 노드 List가 비어있을 경우
                nodes[u] = new ArrayList<>();
            }
            if (nodes[v] == null) {  // v 인덱스의 노드 List가 비어있을 경우
                nodes[v] = new ArrayList<>();
            }

            // 각 노드의 연결점 삽입
            nodes[u].add(v);
            nodes[v].add(u);
        }

        findParents();  // 부모 찾는 BFS 함수 호출

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findParents() {
        Queue<Integer> queue = new ArrayDeque<>();  // BFS를 돌릴 queue 생성
        queue.add(1);  // 트리의 루트 삽입
        parent[1] = 1;  // 트리 루트 방문 처리

        int i;
        while (!queue.isEmpty()) {  // 큐가 빌 때까지 순회
            i = queue.poll();  // 현재 노드 빼기 (부모 노드)
            for (int j = 0; j < nodes[i].size(); j++) {  // 자식 노드 순회
                if (parent[nodes[i].get(j)] == 0) {  // 방문한 적 없을 경우 (자식 노드일 경우)
                    parent[nodes[i].get(j)] = i;  // 부모 노드 삽입
                    // System.out.println(nodes[i].get(j) + "의 부모 노드는 " + i);
                    queue.add(nodes[i].get(j));  // 자식 노드의 자식 queue에 삽입
                }
            }
        }
    }
}
