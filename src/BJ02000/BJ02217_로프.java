package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BJ02217_로프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] ropes = new Integer[N];

        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes, Collections.reverseOrder());

        int MAX_WEIGHT = ropes[0];
        for (int i = 1; i < N; i++) {
            MAX_WEIGHT = Math.max(MAX_WEIGHT, ropes[i] * (i + 1));
        }

        System.out.println(MAX_WEIGHT);
    }
}
