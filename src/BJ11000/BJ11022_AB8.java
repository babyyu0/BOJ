package BJ11000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ11022_AB8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        StringTokenizer st; int A, B;
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            sb.append("Case #").append(i).append(": ")
                    .append(A).append(" + ").append(B).append(" = ")
                    .append(A + B).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
