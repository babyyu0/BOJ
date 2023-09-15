package BJ10000;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ10816_숫자카드2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        HashMap<Integer, Integer> numberMap = new HashMap<>();  // 카드 개수를 받을 HashMap 초기화

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int card;  // 카드 숫자 받을 int 변수 선언
        for(int i = 0; i < N; i++) {
            card = Integer.parseInt(st.nextToken());
            numberMap.put(card, numberMap.getOrDefault(card, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) sb.append(numberMap.getOrDefault(Integer.parseInt(st.nextToken()), 0)).append(" ");

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
