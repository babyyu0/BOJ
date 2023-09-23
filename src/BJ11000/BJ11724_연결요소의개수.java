package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11724_연결요소의개수 {
    private static int N, M;
    private static List<Integer>[] nodeList;
    private static boolean[] visited;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        int n1, n2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            if(nodeList[n1] == null) nodeList[n1] = new ArrayList<>();
            if(nodeList[n2] == null) nodeList[n2] = new ArrayList<>();

            nodeList[n1].add(n2);
            nodeList[n2].add(n1);
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if(!visited[i]/* && nodeList[i] != null*/) {
                visited[i] = true;
                count += bfs(i);
            }
        }

        System.out.println(count);
    }

    private static int bfs(int curNode) {
        queue.clear();
        queue.add(curNode);

        while(!queue.isEmpty()) {
            curNode = queue.poll();
            if(nodeList[curNode] == null) continue;
            for (int i = 0; i < nodeList[curNode].size(); i++) {
                // 방문하지 않은 노드 존재 시
                if(!visited[nodeList[curNode].get(i)]) {
                    visited[nodeList[curNode].get(i)] = true;
                    queue.add(nodeList[curNode].get(i));
                }
            }
        }

        return 1;
    }
}
