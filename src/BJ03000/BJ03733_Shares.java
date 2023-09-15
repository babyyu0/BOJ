package BJ03000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ03733_Shares {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = 0, S = 0;

        try {
            while (true) {
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                S = Integer.parseInt(st.nextToken());
                sb.append(S / (N + 1)).append("\n");
            }
        } catch(Exception e) {
            bw.append(sb.toString());
            bw.flush();
            bw.close();
            br.close();
        }
    }
}
