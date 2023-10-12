package BJ17000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ17626_FourSquares {
    private static int N, min;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        min = N;
        visited = new int[N + 1];
        Arrays.fill(visited, N);

        dp(N, 0);
        System.out.println(min);
    }

    private static void dp(int num, int count) {
        if(num <= 3) {
            min = Math.min(count + num, min);
            return;
        }
        for (int i = (int) Math.sqrt(num); 2 <= i; i--) {
            int tmp = num - (int) Math.pow(i, 2);
            if(count + 1 < visited[tmp]) {
                visited[tmp] = count + 1;
                dp(tmp, count + 1);
            }
        }
    }
}
