package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01865_웜홀 {
    private static final int MAX_EDGE = Integer.MAX_VALUE, MIN_EDGE = Integer.MIN_VALUE;
    private static int T;  // 테스트케이스
    private static int N, M, W; // 노드, 간선, 웜홀의 개수
    private static int[][] edge;
    private static long[][] dist;
    private static Queue<Node> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        queue = new ArrayDeque<>();

        boolean flag;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            flag = false;
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

                dist[s][e] = Math.min(dist[s][e], Integer.parseInt(st.nextToken()));  // 거리 받기
                dist[e][s] = dist[s][e]; // 반대 방향에도 거리 삽입
            }

            // 단방향 그래프 (웜홀)
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());  // 시작점
                e = Integer.parseInt(st.nextToken());  // 끝점
                dist[s][e] = Integer.parseInt("-" + st.nextToken());  // 거리 받기
            }

            findDist();

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j < N; j++) {
                    if (dist[i][j] != MAX_EDGE && dist[j][i] != MAX_EDGE && dist[i][j] + dist[j][i] < 0) {
                        flag = true;
                        break;
                    }
                }
            }

            sb.append((flag) ? "YES" : "NO").append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findDist() {
        for (int i = 0; i <= N; i++) {  // 해당 노드에서 시작
            for (int j = 1; j <= N; j++) {
                goToEnd(j);
            }
        }
    }

    private static void goToEnd(int startNode) {
        dist[startNode][startNode] = 0;  // 시작점과 끝점이 같을 경우에 대한 0 처리

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[startNode][j] < dist[startNode][i] + dist[i][j]) continue;
                dist[startNode][j] = dist[startNode][i] + dist[i][j];
            }
        }
    }

    private static class Node {
        int index;
        long depth;
        protected Node(int index, long depth) {
            this.index = index;
            this.depth = depth;
        }
    }
}