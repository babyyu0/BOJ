package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14938_서강그라운드 {
    private static final int DIV = 10000;
    private static int n, m, r, MAX_ITEMS; // 지역 개수, 수색범위, 길의 개수
    private static int[] items;
    private static int[][] roads, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        roads = new int[n + 1][n + 1];
        dist = new int[n + 1][n + 1];
        items = new int[n + 1];

        // 아이템 개수 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            Arrays.fill(roads[i], DIV);
            Arrays.fill(dist[i], DIV);
        }

        // 길 입력
        int a, b, l;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            roads[a][b] = l;
            roads[b][a] = l;  // 수색 범위 입력
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                MAX_ITEMS = Math.max(MAX_ITEMS, findItems(j, new boolean[n + 1]));
            }
        }

        System.out.println(MAX_ITEMS);
    }

    private static int findItems(int start, boolean[] visited) {
        int getItems = 0;
        dist[start][start] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[start][i] + roads[i][j] < dist[start][j]) {
                    dist[start][j] = dist[start][i] + roads[i][j];
                }
                if (dist[start][j] <= m && !visited[j]) {
                    visited[j] = true;
                    getItems += items[j];
                }
            }
        }

        /*System.out.println(start + "에서 내렸을 때의 거리 차이");
        for (int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(dist[i]));
        }*/
        // System.out.println("이 때의 최대 아이템 획득 수 : " + getItems + "\n");

        return getItems;
    }
}
