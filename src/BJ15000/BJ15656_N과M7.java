package BJ15000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15656_N과M7 {
    private static StringBuilder sb;
    private static int N, M;
    private static int[] nums;

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
        selectNum(0, 0, new int[M]);

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void selectNum(int start, int count, int[] selected) {
        if (count == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < N; i++) {
            selected[count] = nums[i];
            selectNum(start, count + 1, selected);
        }
    }
}
