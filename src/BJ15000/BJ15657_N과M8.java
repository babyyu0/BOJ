package BJ15000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15657_N과M8 {
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

        combination(1, 0, new int[M + 1]);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int count, int start, int[] selectedNums) {  // 순열 함수
        if (count == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selectedNums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            selectedNums[count] = nums[i];
            combination(count + 1, i, selectedNums);
        }
    }
}
