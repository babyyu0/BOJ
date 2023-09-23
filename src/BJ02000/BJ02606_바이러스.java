package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ02606_바이러스 {
    private static final int FIRST_NODE = 1;
    private static int N, M;  // 컴퓨터 수, 쌍의 수
    private static List<Integer>[] lists;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        queue = new ArrayDeque<>();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        lists = new List[N + 1];
        // Arrays.fill(lists, new ArrayList<>());

        int n1, n2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());

            if(lists[n1] == null) lists[n1] = new ArrayList<>();
            if(lists[n2] == null) lists[n2] = new ArrayList<>();
            lists[n1].add(n2);
            lists[n2].add(n1);
        }

        if(lists[1] == null) System.out.println(0);
        else System.out.println(countNode(new boolean[N + 1], 0));
    }

    private static int countNode(boolean[] visited, int count) {
            queue.add(FIRST_NODE);
            visited[FIRST_NODE] = true;

            int curNode;
            while(!queue.isEmpty()) {
                curNode = queue.poll();
                // System.out.println(curNode + "의 연결 노드 = " + lists[curNode]);
                count++;
                for (int i = 0; i < lists[curNode].size(); i++) {
                    if(!visited[lists[curNode].get(i)]) {
                        queue.add(lists[curNode].get(i));
                        // System.out.println(lists[curNode].get(i) + " 방문!");
                        visited[lists[curNode].get(i)] = true;
                    }
                }
            }

            return count - 1;
    }
}
