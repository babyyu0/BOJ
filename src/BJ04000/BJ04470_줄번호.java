package BJ04000;

import java.io.*;

public class BJ04470_줄번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            sb.append(i).append(". ").append(br.readLine()).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
