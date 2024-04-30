package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01735_분수합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(br.readLine());  // 첫번째 분자 분모
        long ja = Long.parseLong(st.nextToken());
        long mo = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());  // 두번째 분자 분모
        long ja2 = Long.parseLong(st.nextToken());  // 두번째 분자
        long mo2 = Long.parseLong(st.nextToken());

        ja = ja * mo2 + ja2 * mo;
        mo  *= mo2;

        long num = Math.min(ja, mo);
        while(1 < num) {
            if(ja % num == 0 && mo % num == 0) {
                ja /= num;
                mo /= num;
                num = Math.min(ja, mo);
            }
            num--;
        }

        System.out.println(ja + " " + mo);
    }
}
