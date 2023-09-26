package BJ02000;

import java.io.*;
import java.util.*;

public class BJ02667_단지번호붙이기 {
    private static class Pos {
        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        int r, c;
    }
    private static int N, perCount;  // 변의 수
    private static boolean[][] visited;  // 방문 여부
    private static List<Integer> list;
    private static final int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        list = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        char[] chars;
        for (int i = 0; i < N; i++) {
            chars = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                visited[i][j] = (chars[j] == '0');
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
                    list.add(bfs(i, j, new ArrayDeque<>()));
                }
            }
        }
        Collections.sort(list);

        sb.append(list.size()).append("\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static Pos curPos;
    private static int bfs(int r, int c, Queue<Pos> queue) {
        perCount = 1;
        queue.add(new Pos(r, c));
        while(!queue.isEmpty()) {
            curPos = queue.poll();
            for (int i = 0; i < 4; i++) {
                if(0 <= (curPos.r + dr[i]) && (curPos.r + dr[i]) < N
                    && 0 <= (curPos.c + dc[i]) && (curPos.c + dc[i]) < N
                    && !visited[curPos.r + dr[i]][curPos.c + dc[i]]) {
                    visited[curPos.r + dr[i]][curPos.c + dc[i]] = true;
                    perCount++;
                    queue.add(new Pos(curPos.r + dr[i],curPos.c + dc[i]));
                }
            }
        }

        return perCount;
    }

}
