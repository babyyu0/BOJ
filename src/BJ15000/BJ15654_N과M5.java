package BJ15000;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ15654_N과M5 {

    private static int N, M;
    private static int[] nums;
    private static StringBuilder sb;
    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        set = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        dfs(0, "", new boolean[N]);

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int count, String numsList, boolean[] visited) {
        if (count == M && !set.contains(numsList)) {
            set.add(numsList);
            sb.append(numsList).append("\n");
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(count + 1, numsList + nums[i] + " ", visited);
                visited[i] = false;
            }
        }
    }
}
