package BJ09000;

import java.io.*;
import java.util.*;

public class BJ09375_패션왕신해빈 {
    private static int T, N;
    private static Integer[] clothes;
    private static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        map = new HashMap<>();

        T = Integer.parseInt(br.readLine());

        String cloth, category;
        for (int test_case = 0; test_case < T; test_case++) {
            N = Integer.parseInt(br.readLine());
            map.clear();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                cloth = st.nextToken();
                category = st.nextToken();

                map.put(category, map.getOrDefault(category, 1) + 1);
            }

            clothes = map.values().toArray(new Integer[0]);
            int sum = 1;
            for (int i = 0; i < clothes.length; i++) {
                sum *= clothes[i];
            }
            sb.append(sum - 1).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
