package BJ15000;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BJ15666_N과M12 {
    private static int N, M;
    private static List<Integer> list;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        list = set.stream().sorted().collect(Collectors.toList());
        // System.out.println(list);

        combination(0, 0, new int[M + 1]);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void combination(int count, int start, int[] selected) {
        if(count == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < list.size(); i++) {
            selected[count] = list.get(i);
            combination(count + 1, i, selected);
        }
    }
}
