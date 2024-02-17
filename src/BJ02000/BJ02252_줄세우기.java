package BJ02000;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ02252_줄세우기 {
    private static class Node {
        public Node(int index, long rank) {
            this.index = index;
            this.rank = rank;
        }

        int index;
        long rank;

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", rank=" + rank +
                    '}';
        }
    }
    private static int N, M;  // 학생 N명, 키 비교 횟수 M번
    private static StringBuilder sb;
    private static List<Integer>[] nodes;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        int a, b;  // 학생들을 임의로 저장할 변수 선언

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(nodes[b] == null) nodes[b] = new ArrayList<>();
            nodes[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) {
                dfs(i);
                //System.out.println(" 방문");
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static void dfs(int n) {
        visited[n] = true;

        if(nodes[n] != null) {
            for (int parent : nodes[n]) {
                if(!visited[parent]) dfs(parent);
            }
        }

        sb.append(n).append(" ");
        //System.out.print(n);
    }
}

// 1 2 3 2