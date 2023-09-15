package BJ04000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ4153_직각삼각형 {

    private static final int FIGURE_SIZE = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[] side = new int[3];  // 세 변의 길이
        while(true) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < FIGURE_SIZE; i++) {
                side[i] = Integer.parseInt(st.nextToken());
            }
            if(side[0] == 0 && side[1] == 0 && side[2] == 0) break;

            Arrays.sort(side);

            if(Math.pow(side[0], 2) + Math.pow(side[1], 2) == Math.pow(side[2], 2)) {
                sb.append("right").append("\n");
                continue;
            } else {
                sb.append("wrong").append("\n");
            }
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
