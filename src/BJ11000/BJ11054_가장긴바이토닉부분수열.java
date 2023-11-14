package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11054_가장긴바이토닉부분수열 {
    private static int N, MAX;
    private static int[] NUMS;
    private static int[][] DP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // MAX = 1;

        N = Integer.parseInt(br.readLine());
        NUMS = new int[N];
        DP = new int[2][N];  // 최장 길이를 구할 배열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            NUMS[i] = Integer.parseInt(st.nextToken());
        }

        findLongestNumList();
        System.out.println(MAX);
    }

    private static void findLongestNumList() {
        Arrays.fill(DP[0], 1);
        Arrays.fill(DP[1], 1);

        for (int i = 1; i < N; i++) {
            for (int j = i - 1; 0 <= j; j--) {
                if(NUMS[j] < NUMS[i]) {
                    DP[0][i] = Math.max(DP[0][i], DP[0][j] + 1);
                }
            }
        }

        for (int i = N - 1; 0 <= i; i--) {
            for (int j = i + 1; j < N; j++) {
                if(NUMS[j] < NUMS[i]) {
                    DP[1][i] = Math.max(DP[1][i], DP[1][j] + 1);
                    // DP[1][i] = DP[1][j] + 1;
                }
            }
            MAX = Math.max(MAX, DP[0][i] + DP[1][i] - 1);
        }
        // System.out.println("증가 수열: " + Arrays.toString(DP[0]));
        // System.out.println("감소 수열: " + Arrays.toString(DP[1]));
    }
}

