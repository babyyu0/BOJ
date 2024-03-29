package BJ15000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15486_퇴사2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int day = Integer.parseInt(br.readLine());
        long[] pays = new long[day + 1];

        StringTokenizer st;
        int T, P, afterDay;
        for (int i = 1; i <= day; i++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            afterDay = i + T - 1;

            if (pays[i] < pays[i - 1]) pays[i] = pays[i - 1];
            if (afterDay <= day) {
                pays[afterDay] = Math.max(pays[i - 1] + P, pays[afterDay]);
            }
        }
        System.out.println(pays[day]);
    }
}
