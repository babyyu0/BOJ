package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01057_토너먼트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        st.nextToken();
        int tmp;
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        tmp = Math.max(a, b);
        a = Math.min(a, b);
        b = tmp;

        int i = 1;
        while(1 < b - a || b % 2 != 0) {
            //System.out.println("a: " + a + ", b: " + b);
            i++;
            a = a / 2 + (a % 2);
            b = b / 2 + (b % 2);
        }

        //System.out.println("a: " + a + ", b: " + b);
        System.out.println(i);


    }

}
