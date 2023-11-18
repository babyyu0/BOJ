package BJ12000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ12850_숨바꼭질2 {
    private static int N, K;
    private static final int DIV = 100000;
    private static int[][] move;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        move = new int[DIV + 1][2];
        for (int i = 0; i <= DIV; i++) {
            move[i][0] = DIV;
        }

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // dfs(N, 0);
        findFast();
        System.out.println(move[K][0]);
        System.out.println(move[K][1]);
    }

    private static void findFast() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> move[o][0]));
        pq.add(N);
        move[N][0] = 0;
        move[N][1] = 1;

        int index;
        while(!pq.isEmpty()) {
            // System.out.println(pq);
            index = pq.poll();

            if(index * 2 <= K + N && index * 2 <= 100000 && move[index][0] < move[index * 2][0]) {
                if(move[index * 2][0] == move[index][0] + 1) move[index * 2][1]++;
                else move[index * 2][1] = 1;
                move[index * 2][0] = move[index][0] + 1;
                pq.add(index * 2);
            }
            if(index + 1 <= K && move[index][0] < move[index + 1][0]) {
                if(move[index + 1][0] == move[index][0] + 1) move[index + 1][1]++;
                else move[index + 1][1] = 1;
                move[index + 1][0] = move[index][0] + 1;
                pq.add(index + 1);
            }
            if(0 <= index - 1 && move[index][0] < move[index - 1][0]) {
                if(move[index - 1][0] == move[index][0] + 1) move[index - 1][1]++;
                else move[index - 1][1] = 1;
                move[index - 1][0] = move[index][0] + 1;
                pq.add(index - 1);
            }
        }
    }
}
