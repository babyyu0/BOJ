package BJ16000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ16139_인간컴퓨터상호작용 {
    private static final int ALPHA_MAX = 26;
    private static final int ALPHA2LOWER = 97;
    private static long[][] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        char[] str = br.readLine().toCharArray();  // 문자열 받기
        int STR_LENGTH = str.length;
        alpha = new long[ALPHA_MAX][STR_LENGTH + 1];
        for (int i = 1; i <= STR_LENGTH; i++) {
            refreshCount(i);  // 이전 값으로 변경
            alpha[str[i - 1] - ALPHA2LOWER][i]++;  // 개수 하나 더하기
        }

        int N = Integer.parseInt(br.readLine());
        int a, l, r;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = st.nextToken().charAt(0) - ALPHA2LOWER;
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            sb.append(alpha[a][r + 1] - alpha[a][l]).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void refreshCount(int point) {
        for (int j = 0; j < ALPHA_MAX; j++) {
            alpha[j][point] = alpha[j][point - 1];
        }
    }
}