package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01182_부분수열의합 {
    private static int N, COUNT;
    private static long S;
    private static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Long.parseLong(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        combination(0, 0, 0);
        System.out.println(COUNT - (S == 0 ? 1 : 0));
    }

    private static void combination(int start, int count, long sum) {
        if(sum == S) COUNT++;
        if(count == N) {
            return;
        }

        for (int i = start; i < N; i++) {
            combination(i + 1, count + 1, sum + nums[i]);
        }
    }
}
