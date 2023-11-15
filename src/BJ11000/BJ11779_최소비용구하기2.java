package BJ11000;

import java.io.*;
import java.util.*;

public class BJ11779_최소비용구하기2 {

    private static class Bus {
        Bus(int index, int weight) {
            this.index = index; this.weight = weight;
            this.list = new ArrayList<>();
        }
        int index, weight;
        List<Integer> list;

        @Override
        public String toString() {
            return "Bus{" +
                    "index=" + index +
                    ", weight=" + weight +
                    ", list=" + list +
                    '}';
        }
    }
    private static int N, M, S, E, MIN;
    // private static List<Bus>[] nodes;
    private static List<Bus>[] nodes;
    private static boolean[] visited;
    private static List<Integer> maxList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        MIN = Integer.MAX_VALUE;
        nodes = new ArrayList[1001];
        visited = new boolean[1001];

        N = Integer.parseInt(br.readLine());  // 도시 개수
        M = Integer.parseInt(br.readLine());  // 버스 개수

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            if(nodes[S] == null) {
                nodes[S] = new ArrayList<>();
            }
            nodes[S].add(new Bus(E, Integer.parseInt(st.nextToken())));
        }
        // System.out.println(Arrays.toString(nodes));

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());  // 출발점
        E = Integer.parseInt(st.nextToken());  // 도착점

        dajikstra();

        sb.append(MIN).append("\n");
        sb.append(maxList.size()).append("\n");
        // System.out.println(maxList);
        for (int i = 0; i < maxList.size(); i++) {
            sb.append(maxList.get(i)).append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    /*private static void dajikstra() {
        int[] dist = new int[1001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;


    }*/
    private static void dajikstra() {
        PriorityQueue<Bus> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        Bus bus = new Bus(S, 0);
        bus.list.add(S);
        pq.add(bus);
        visited[S] = true;

        Bus newBus;
        while(!pq.isEmpty()) {
            bus = pq.poll();
            if(bus.index == E && bus.weight <= MIN) {
                MIN = bus.weight;
                maxList = bus.list;
                return;
            }
            visited[bus.index] = true;

            if(nodes[bus.index] == null) continue;
            for (int i = 0; i < nodes[bus.index].size(); i++) {
                if(!visited[nodes[bus.index].get(i).index] && bus.weight + nodes[bus.index].get(i).weight <= MIN) {
                    newBus = new Bus(nodes[bus.index].get(i).index, bus.weight + nodes[bus.index].get(i).weight);
                    newBus.list.addAll(bus.list);
                    newBus.list.add(nodes[bus.index].get(i).index);
                    pq.add(newBus);
                }
            }
        }
    }
}
