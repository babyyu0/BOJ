package BJ11000;

import java.io.*;
import java.util.*;

public class BJ11403_경로찾기 {
    private static int N, curNode;
    private static List<Integer>[] nodeList;
    private static boolean[] visited;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        queue = new ArrayDeque<>();

        N = Integer.parseInt(br.readLine());
        nodeList = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nodeList[i] = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    nodeList[i].add(j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                queue.clear();
                visited = new boolean[N];
                sb.append(bfs(i, j)).append(" ");
            }
            sb.append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int start, int end) {
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            curNode = queue.poll();

            for (int i = 0; i < nodeList[curNode].size(); i++) {
                if (nodeList[curNode].get(i) == end) {
                    return 1;
                } else if (!visited[nodeList[curNode].get(i)]) {
                    visited[nodeList[curNode].get(i)] = true;
                    queue.add(nodeList[curNode].get(i));
                }
            }
        }
        return 0;
    }
}
