package BJ04000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ04562_NoBrainer {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            System.out.println((Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken())  < 0) ? "NO BRAINS" : "MMM BRAINS");
        }
    }
}
