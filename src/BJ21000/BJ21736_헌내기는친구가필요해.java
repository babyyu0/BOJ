package BJ21000;

import java.io.*;
import java.util.*;

public class BJ21736_헌내기는친구가필요해 {
    private static class Node {
        public Node(int r, int c) { this.r = r; this.c = c;}
        int r, c;
    }
    private static int N, M;
    private static Queue<Node> queue;
    private static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        queue = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == 'I') {
                    queue.add(new Node(i, j)); // r, c
                    map[i][j] = 'X';
                }
            }
        }

        System.out.println(bfs(0));
    }

    private static String bfs(int count) {
        Node curNode;
        while(!queue.isEmpty()) {
            curNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                if(0 <= curNode.r + dr[i] && curNode.r + dr[i] < N
                && 0 <= curNode.c + dc[i] && curNode.c + dc[i] < M
                && map[curNode.r + dr[i]][curNode.c + dc[i]] != 'X') {  // 방문 가능할 때
                    if(map[curNode.r + dr[i]][curNode.c + dc[i]] == 'P') {
                        count++;
                    }
                    map[curNode.r + dr[i]][curNode.c + dc[i]] = 'X';  // 방문 처리
                    queue.add(new Node(curNode.r + dr[i], curNode.c + dc[i]));
                }
            }
        }
        return (count == 0)? "TT" : Integer.toString(count);
    }
}
