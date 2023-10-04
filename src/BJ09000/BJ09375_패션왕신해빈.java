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

                map.put(category, map.getOrDefault(category, 0) + 1);
            }

            clothes = map.values().toArray(new Integer[0]);
            sum = 0;
            calNum(-1, 0, clothes.length, 1);
            sb.append(sum + N).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int sum = 0;
    private static void calNum(int start, int count, int max, int res) {  // 경우의 수 뽑기
        if(count == max) {
            System.out.println((res) + "더하기");
            sum += res;
            return;
        }
        for (int i = start + 1; i < clothes.length; i++) {
            System.out.println((res) + "더하기");
            sum += res;
            calNum(i, count + 1, max, res * clothes[i]);
        }

    }
}
