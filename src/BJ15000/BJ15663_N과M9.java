package BJ15000;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ15663_N과M9 {
    private static int N, M;
    private static int[] nums;
    private static Set<String> set;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        set = new HashSet<>();  // String 방문 처리할 set

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);  // 오름차순 정렬을 위한 정렬
        findComb(0, "", new boolean[N]);

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findComb(int count, String comb, boolean[] visited) {
        if(count == M) {
            if(!set.contains(comb)) {
                sb.append(comb).append("\n");
                set.add(comb);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                findComb(count + 1, comb + nums[i] + " ", visited);
                visited[i] = false;
            }
        }
    }
}
