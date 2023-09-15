package BJ11000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11047_동전0 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] coins = new int[Integer.parseInt(st.nextToken())];  // 동전 종류를 담을 배열 coins
        int K = Integer.parseInt(st.nextToken());  //가치의 합 K

        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);
        int cnt = 0;
        for (int i = coins.length - 1; i >= 0; i--) {
            if(K == 0) break;
            while(K - coins[i] >= 0) {
                K-= coins[i];
                cnt++;
            }
        }

        sb.append(cnt);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
