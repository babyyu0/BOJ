package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ11656_접미사배열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();
        String[] strArr = new String[N];

        for (int i = 0; i < N; i++) {
            strArr[i] = str.substring(i, N);
        }
        Arrays.sort(strArr);

        for (int i = 0; i < N; i++) {
            System.out.println(strArr[i]);
        }
    }
}
