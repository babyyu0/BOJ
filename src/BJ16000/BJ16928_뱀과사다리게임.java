package BJ16000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ16928_뱀과사다리게임 {

    private static int N, M;
    private static int[] count;
    private static Map<Integer, Integer> ladderMap, snakeMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        count = new int[100 + 1];
        ladderMap = new HashMap<>();
        snakeMap = new HashMap<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {  // 사다리의 수
            st = new StringTokenizer(br.readLine());
            ladderMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {  // 뱀의 수
            st = new StringTokenizer(br.readLine());
            snakeMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        startGame(0);
        startGame(0);

        System.out.println(count[100]);
    }

    private static void startGame(int move) {
        for (int i = 1; i <= 100; i++) {
            if (count[i] == 0 && i != 1) continue;
            for (int j = 1; j <= 6; j++) {
                move = i + j;
                if(100 < move) {
                    continue;
                } else if(ladderMap.containsKey(move)) {
                    move = ladderMap.get(move);
                } else if (snakeMap.containsKey(move)) {
                    move = snakeMap.get(move);
                }

                if (count[move] == 0) {
                    count[move] = count[i] + 1;
                } else {
                    count[move] = Math.min(count[move], count[i] + 1);
                }
            }
        }
    }
}
