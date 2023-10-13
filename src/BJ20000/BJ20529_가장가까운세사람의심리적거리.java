package BJ20000;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ20529_가장가까운세사람의심리적거리 {
    // ISTJ, ISFJ, INFJ, INTJ, ISTP, ISFP, INFP, INTP, ESTP, ESFP, ENFP, ENTP, ESTJ, ESFJ, ENFJ, ENTJ
    private static int[][] mbti;
    private static String[] savedMbti;
    private static int T, N, min;
    private static Map<String, Integer> mbtiMap;

    public static void main(String[] args) throws IOException {
        defaultMbtiMap();
        defaultMbti();

        // 코드 시작
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            min = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            savedMbti = new String[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                savedMbti[i] = st.nextToken();
            }
            if(N > 32) {
                sb.append(0).append("\n");
                continue;
            }

            dfs(-1, 0, new int[3]);

            sb.append(min).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int count, int[] selected) {
        if(count == 3) {
            min = Math.min(min, mbti[selected[0]][selected[1]] + mbti[selected[1]][selected[2]] + mbti[selected[0]][selected[2]]);
            return;
        }
        for (int i = index + 1; i < N; i++) {
            selected[count] = mbtiMap.get(savedMbti[i]);
            dfs(i, count + 1, selected);
        }
    }

    private static void defaultMbtiMap() {
        mbtiMap = new HashMap<>() {{
            put("ISTJ", 0);
            put("ISFJ", 1);
            put("INFJ", 2);
            put("INTJ", 3);
            put("ISTP", 4);
            put("ISFP", 5);
            put("INFP", 6);
            put("INTP", 7);
            put("ESTP", 8);
            put("ESFP", 9);
            put("ENFP", 10);
            put("ENTP", 11);
            put("ESTJ", 12);
            put("ESFJ", 13);
            put("ENFJ", 14);
            put("ENTJ", 15);
        }};
    }
    private static void defaultMbti() {
        mbti = new int[16][16];

        int count;
        for(String curMbti : mbtiMap.keySet()) {
            for(String compMbti : mbtiMap.keySet()) {
                count = 4;
                for (int i = 0; i < 4; i++) {
                    if(curMbti.charAt(i) == compMbti.charAt(i)) count--;
                }
                mbti[mbtiMap.get(curMbti)][mbtiMap.get(compMbti)] = count;
            }
        }
    }
}
