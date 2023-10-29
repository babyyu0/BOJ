package BJ11000;

import java.io.*;
import java.util.*;

public class BJ11404_플로이드 {
    private static class Node {
        private Node(int index, int weight) {
            this.index = index; this.weight = weight;
        }
        int index,weight;
    }
    private static int N;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        int u, v, weight;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(weight, dist[u][v]);
        }

        findMin();
        findMin();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append((dist[i][j] == Integer.MAX_VALUE)? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findMin() {
        Node linked;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {  // i 노드와 연결된 노드 순회
                for (int k = 1; k <= N; k++) {
                    if (dist[i][j] == Integer.MAX_VALUE || dist[j][k] == Integer.MAX_VALUE) continue;
                    dist[i][k] = Math.min(dist[i][k], dist[i][j] + dist[j][k]);
                }
            }
        }
    }
}
