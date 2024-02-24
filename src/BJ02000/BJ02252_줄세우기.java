package BJ02000;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ02252_줄세우기 {
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

        nodes = new ArrayList[N + 1];  // 부모노드를 가리킬 리스트
        visited = new boolean[N + 1];  // 방문 처리

        int a, b;  // 학생들을 임의로 저장할 변수 선언

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (nodes[b] == null) nodes[b] = new ArrayList<>();
            nodes[b].add(a);  // 자식 노드에 부모 노드 연결 (작은 사람이 우선순위 높음)
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {  // 방문하지 않았을 경우
                dfs(i);  // 해당 경로로 거치는 모든 노드 DFS
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

        if (nodes[n] != null) {  // null일 경우 (루트노드일 경우) 바로 출력
            for (int parent : nodes[n]) {
                if (!visited[parent]) dfs(parent);  // 부모 노드로 이동하는 DFS 재귀
            }
        }

        sb.append(n).append(" ");  // 제일 작은 노드부터 삽입
        //System.out.print(n);
    }
}