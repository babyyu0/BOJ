package BJ10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10807_개수세기 {
    public static void main(String[] args) throws IOException {
        int[] counts = new int[201];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            counts[Integer.parseInt(st.nextToken()) + 100]++;
        }

        System.out.println(counts[Integer.parseInt(br.readLine()) + 100]);

    }
}
