package BJ15000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15655_N과M6 {
    private static int N, M;
    private static int[] nums;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        combination(0, 0, "");
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    private static void combination(int start, int count, String str) {
        if(count == M) {
            sb.append(str).append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            combination(i + 1, count + 1, str + nums[i] + " ");
        }
    }
}
