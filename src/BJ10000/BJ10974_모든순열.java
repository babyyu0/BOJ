package BJ10000;

import java.io.*;

public class BJ10974_모든순열 {
    private static StringBuilder sb;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        makePermutation(0, new boolean[N + 1], "");
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void makePermutation(int count, boolean[] visited, String perm) {
        if(count == N) {
            sb.append(perm).append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                makePermutation(count + 1, visited, perm + i + " ");
                visited[i] = false;
            }
        }
    }
}
