package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01912_연속합 {
    private static int N, MAX;
    private static int[] nums, dpNums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        MAX = Integer.MIN_VALUE;

        N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        dpNums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dp();
        // System.out.println(Arrays.toString(dpNums));
        System.out.println(MAX);
    }

    private static void dp() {
        for (int i = 1; i <= N; i++) {
            MAX = Math.max(MAX, dpNums[i] = Math.max(nums[i], dpNums[i - 1] + nums[i]));
        }
    }
}
