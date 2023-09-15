package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ02579_계단오르기 {

    private static int[] dp;
    private static int[] stair;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stair = new int[N + 1];
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(moveStair());
    }

    private static int moveStair() {
        dp[1] = stair[1];
        if(N == 1) return dp[1];

        dp[2] = dp[1] + stair[2];
        if(N == 2) return dp[2];

        dp[3] = Math.max(stair[1] + stair[3], stair[2] + stair[3]);
        if(N == 3) return dp[3];
        
        for (int i = 4; i <= N; i++) {
            dp[i] = stair[i] + Math.max(stair[i - 1] + dp[i - 3], dp[i - 2]);
        }

        return dp[N];
    }
}