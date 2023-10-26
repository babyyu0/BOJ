package BJ09000;

import java.io.*;
import java.util.Arrays;

public class BJ09251_LCS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] str = new String[]{" " + br.readLine().trim(), " " + br.readLine().trim()};

        // 한번 걸러주기


        System.out.println(Math.max(compare(str[0], str[1]), compare(str[1], str[0])));
    }

    private static int compare(String str, String compareStr) {
        int[][] dist = new int[str.length()][compareStr.length()];

        for (int i = 1; i < str.length(); i++) {
            // System.out.println(str.charAt(i) + " 시작");
            for (int j = 1; j < compareStr.length();j++) {
                if(str.charAt(i) == compareStr.charAt(j)) {
                    dist[i][j] = dist[i - 1][j - 1] + 1;
                }
                else dist[i][j] = Math.max(dist[i - 1][j], dist[i][j - 1]);
            }

            // System.out.println(Arrays.deepToString(dist));
        }
        return dist[str.length() - 1][compareStr.length() - 1];

    }
}
