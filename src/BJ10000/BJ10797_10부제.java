package BJ10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10797_10부제 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;

        for (int i = 0; i < 5; i++) {
            if(st.nextToken().equals(N)) answer++;
        }
        System.out.println(answer);
    }
}
