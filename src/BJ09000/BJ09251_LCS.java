package BJ09000;

import java.io.*;
import java.util.Arrays;

public class BJ09251_LCS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] str = new String[]{br.readLine(), br.readLine()};

        // 한번 걸러주기


        System.out.println(compare(str[0], str[1], 0));
    }

    private static int compare(String str, String compareStr, int sum) {
        int[] dist = new int[compareStr.length()];  // {index sum}
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i) + " 시작");
            // for (int j = 0; j < compareStr.length(); j++) {
            for (int j = compareStr.length() - 1; j >= 0; j--) {
                if( 0 <= j - 1) dist[j] = Math.max(dist[j - 1], dist[j]);
                if(str.charAt(i) == compareStr.charAt(j)) {
                    if( 0 <= j - 1) dist[j] = Math.max(dist[j - 1] + 1, dist[j]);
                    else dist[j] = 1;
                }
            }
            System.out.println(Arrays.toString(dist));
        }
        return sum;

    }
}
