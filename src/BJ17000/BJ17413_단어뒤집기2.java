package BJ17000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ17413_단어뒤집기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        String[] strArr;
        char[] charArr;
        String str; int left, right;
        while(st.hasMoreTokens()) {
            strArr = st.nextToken().split("<");
            charArr = st.nextToken().toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                if(charArr[i] == '<') {
                    while(charArr[i] != '>') {
                        sb.append(charArr[i++]);
                    }
                    sb.append(charArr[i]);
                } else {
                    while(i < )
                }
            }


            sb.append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
