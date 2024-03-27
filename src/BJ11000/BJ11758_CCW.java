package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11758_CCW {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long[][] nodes = new long[3][2];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i][0] = Long.parseLong(st.nextToken());
            nodes[i][1] = Long.parseLong(st.nextToken());
        }

        long y = (nodes[1][0] - nodes[0][0]) * (nodes[2][1] - nodes[0][1]) - (nodes[1][1] - nodes[0][1]) * (nodes[2][0] - nodes[0][0]);

        if (y > 0) System.out.println(1);
        else if (y < 0) System.out.println(-1);
        else System.out.println(0);
    }
}
