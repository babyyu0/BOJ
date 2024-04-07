package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ01922_네트워크연결 {
    private static class Edge {
        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        int a, b, cost;

        @Override
        public String toString() {
            return "Edge{" +
                    "a=" + a +
                    ", b=" + b +
                    ", cost=" + cost +
                    '}';
        }
    }

    private static int N, M, SUM;
    private static int[] nodes;
    private static List<Edge> costList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        nodes = new int[N + 1];
        Arrays.fill(nodes, N + 1);
        costList = new ArrayList<>();

        StringTokenizer st;
        int a, b, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            if (a == b) continue;

            costList.add(new Edge(a, b, cost));
        }
        costList.sort(Comparator.comparingInt(o -> o.cost));
//        System.out.println(Arrays.toString(nodes));
//        System.out.println(Arrays.toString(costs));
        findMinLink();
        System.out.println(SUM);
    }

    private static void findMinLink() {
        int aParent, bParent;
        for (int i = 0; i < costList.size(); i++) {
            aParent = find(costList.get(i).a);
            bParent = find(costList.get(i).b);
            if (aParent < bParent) {
                nodes[bParent] = aParent;
                //System.out.printf("%d번 노드를 %d에 연결\n", bParent, aParent);
                SUM += costList.get(i).cost;
            } else if (aParent > bParent) {
                nodes[aParent] = bParent;
                //System.out.printf("%d번 노드를 %d에 연결\n", aParent, bParent);
                SUM += costList.get(i).cost;
            }
            //System.out.println(Arrays.toString(nodes));
        }
    }

    private static int find(int child) {
        if (nodes[child] == N + 1) {
            return child;
        }
        return nodes[child] = find(nodes[child]);
    }
}
