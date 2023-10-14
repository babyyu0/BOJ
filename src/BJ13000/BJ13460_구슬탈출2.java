package BJ13000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ13460_구슬탈출2 {
    private static class Nodes implements Comparable<Nodes> {
        protected Nodes() {
            count = 0;
        }
        protected Nodes(int redR, int redC, int blueR, int blueC, int count) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.count = count;
        }

        int redR, redC, blueR, blueC, count;

        @Override
        protected Nodes clone() throws CloneNotSupportedException {
            return new Nodes(this.redR, this.redC, this.blueR, this.blueC, this.count);
        }

        @Override
        public int compareTo(Nodes o) {
            return ((this.redR == o.redR && this.redC == o.redC)? 0: 1) + ((this.blueR == o.blueR && this.blueC == o.blueC)? 0: 1);
        }
    }

    private static int N, M, min;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static Nodes nodes;
    private static char[][] map;
    private static Queue<Nodes> queue;

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        nodes = new Nodes();

        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'R') {  // 빨간 공 위치 저장
                    nodes.redR = i;
                    nodes.redC = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {  // 파란 공 위치 저장
                    nodes.blueR = i;
                    nodes.blueC = j;
                    map[i][j] = '.';
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() throws CloneNotSupportedException {
        Set<Nodes> set = new HashSet<>();
        //boolean[][] redVisited = new boolean[N][M], blueVisited = new boolean[N][M];

        queue.add(nodes.clone());

        Nodes curNodes, pollNodes;
        while (!queue.isEmpty()) {
            pollNodes = queue.poll();
            for (int i = 0; i < 4; i++) {
                curNodes = pollNodes.clone();
                curNodes.count++;
                moveRed(curNodes, i);
                moveBlue(curNodes, i);
                if(map[curNodes.redR][curNodes.redC] == 'O' && map[curNodes.blueR][curNodes.blueC] != 'O') return curNodes.count;
                else if(map[curNodes.blueR][curNodes.blueC] == 'O') continue;

                moveRed(curNodes, i);
                moveBlue(curNodes, i);
                if(map[curNodes.redR][curNodes.redC] == 'O' && map[curNodes.blueR][curNodes.blueC] != 'O') return curNodes.count;
                else if(map[curNodes.blueR][curNodes.blueC] == 'O') continue;

                if(set.contains(curNodes)) continue;
                if(curNodes.count + 1 <= 10)  {
                    set.add(curNodes.clone());
                    queue.add(curNodes.clone());
                }
            }
        }
        return -1;
    }

    private static void moveRed(Nodes curNodes, int i) {
        int mr = curNodes.redR + dr[i], mc = curNodes.redC + dc[i];
        while (0 < mr && mr < N && 0 < mc && mc < M) {  // 빨간 공
            if (map[mr][mc] == '.' && !(mr == curNodes.blueR && mc == curNodes.blueC)) {
                curNodes.redR = mr;
                curNodes.redC = mc;
                mr = curNodes.redR + dr[i];
                mc = curNodes.redC + dc[i];
                continue;
            } else if (map[mr][mc] == 'O') {
                curNodes.redR = mr;
                curNodes.redC = mc;
            }
            return;
        }
    }
    private static void moveBlue(Nodes curNodes, int i) {
        int mr = curNodes.blueR + dr[i], mc = curNodes.blueC + dc[i];
        while (0 < mr && mr < N && 0 < mc && mc < M) {  // 파란 공
            if (map[mr][mc] == '.' && !(mr == curNodes.redR && mc == curNodes.redC)) {
                curNodes.blueR = mr;
                curNodes.blueC = mc;
                mr = curNodes.blueR + dr[i];
                mc = curNodes.blueC + dc[i];
                continue;
            } else if (map[mr][mc] == 'O') {
                curNodes.blueR = mr;
                curNodes.blueC = mc;
            }
            return;
        }
    }

    private static void printMap(Nodes curNodes) {
        System.out.println("=== " + (curNodes.count + 1) + " ===");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(i == curNodes.redR && j == curNodes.redC) {
                    System.out.print("R");
                } else if(i == curNodes.blueR && j == curNodes.blueC) {
                    System.out.print("B");
                } else {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
