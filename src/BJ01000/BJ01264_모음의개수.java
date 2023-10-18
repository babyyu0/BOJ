package BJ01000;

import java.io.*;

public class BJ01264_모음의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str;
        int count;
        while(true) {
            str = br.readLine().toLowerCase();
            if(str.trim().equals("#")) break;
            count = 0;
            for (int i = 0; i < str.length(); i++) {
                if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') count++;
            }
            sb.append(count).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
