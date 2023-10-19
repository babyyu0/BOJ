package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01865_웜홀 {
    private static final int MAX_EDGE = 10001;
    private static int T;  // 테스트케이스
    private static int N, M, W; // 노드, 간선, 웜홀의 개수
    private static int[][] edge;
    private static long[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 노드의 개수
            M = Integer.parseInt(st.nextToken());  // 간선의 개수
            W = Integer.parseInt(st.nextToken());  // 웜홀의 개수
            edge = new int[N + 1][N + 1];  // 노드의 개수만큼 간선 초기화
            dist = new long[N + 1][N + 1];  // 시작점부터 끝점까지의 거리 초기화

            for (int i = 1; i <= N; i++) {
                Arrays.fill(edge[i], MAX_EDGE);  // 간선 값을 최대값으로 초기화
                Arrays.fill(dist[i], MAX_EDGE);  // 간선 값을 최대값으로 초기화
            }

            int s, e;  // 노드 번호 담을 임의의 변수 선언
            // 무방향 그래프 (도로)
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());  // 시작점
                e = Integer.parseInt(st.nextToken());  // 끝점

                edge[s][e] = Math.min(edge[s][e], Integer.parseInt(st.nextToken()));  // 거리 받기
                edge[e][s] = edge[s][e]; // 반대 방향에도 거리 삽입
            }

            // 단방향 그래프 (웜홀)
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());  // 시작점
                e = Integer.parseInt(st.nextToken());  // 끝점
                edge[s][e] = Integer.parseInt("-" + st.nextToken());  // 거리 받기
            }

            sb.append(findDist()).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String findDist() {
        for (int i = 1; i <= N; i++) {  // 해당 노드에서 시작
            for (int j = 0; j < N; j++) {
                dist[i][i] = 0;
                if(!goToEnd(i)) break;
            }
            if(goToEnd(i)) return "YES";
        }

        return "NO";
    }

    private static boolean goToEnd(int startNode) {
        boolean flag = false;
        long curDepth;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                curDepth = dist[startNode][i] + edge[i][j];
                if(dist[startNode][j] <= curDepth) continue;
                flag = true;
                dist[startNode][j] = curDepth;
            }
        }
        // System.out.println(startNode + "부터의 정점: " + Arrays.toString(dist[startNode]));

        return flag;
    }
}