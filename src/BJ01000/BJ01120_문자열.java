package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01120_문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();

        int cnt, MIN = A.length();
        String tmp;
        for (int i = 0; i + A.length() <= B.length(); i++) {
            cnt = 0;
            tmp = B.substring(i, i + A.length());
            for (int j = 0; j < A.length(); j++) {
                cnt += (A.charAt(j) == tmp.charAt(j)) ? 0 : 1;
                if(MIN <= cnt) break;
            }
            //System.out.println(A + "와 " + tmp + "의 차이 개수는 " + cnt + "개");
            MIN = Math.min(cnt, MIN);
        }

        System.out.println(MIN);
    }
}
