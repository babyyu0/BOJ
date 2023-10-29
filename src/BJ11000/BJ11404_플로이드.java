package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11404_플로이드 {
    private static class Node {
        private Node(int index, int weight) {
            this.index = index; this.weight = weight;
        }
        int index,weight;
    }
    private static int N, M;
    private static List<Node>[] edge;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edge = new ArrayList[N + 1];
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

            if(edge[u] == null) {
                edge[u] = new ArrayList<>();
            }
            edge[u].add(new Node(v, weight));
        }

        findMin();
    }

    private static void findMin() {

        for (int i = 1; i <= N; i++) {
            if(edge[i] == null) continue;
            for (int j = 0; j < edge[i].size(); j++) {
                if(edge[j] == null) continue;
                for (int k = 0; k < edge[j].size(); k++) {
                    dist[edge[i].get(j).index][edge[j].get(k).index] = Math.min(dist[i][edge[j].get(k).index], dist[i][j] + edge[j].get(k).weight);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }

    }
}
