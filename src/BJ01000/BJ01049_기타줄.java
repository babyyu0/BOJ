package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01049_기타줄 {
    public static void main(String[] args) throws IOException {
        int MIN = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int C = 1001, V = 1001, tmp;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            C = Math.min(Integer.parseInt(st.nextToken()), C);
            V = Math.min(Integer.parseInt(st.nextToken()), V);
        }
        MIN = V * N;
        MIN = Math.min(MIN, C  * (int) Math.ceil(N / 6.0));
        MIN = Math.min(MIN, C  * (int) Math.floor(N / 6.0) + V * (N % 6));

        System.out.println(MIN);
    }
}
