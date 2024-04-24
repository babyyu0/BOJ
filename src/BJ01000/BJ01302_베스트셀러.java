package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BJ01302_베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        String bookName = br.readLine();
        map.put(bookName, 1);
        String answer = bookName;
        for (int i = 1; i < N; i++) {
            bookName = br.readLine();
            map.put(bookName, map.getOrDefault(bookName, 0) + 1);
            if(map.get(answer) < map.get(bookName) || map.get(answer).intValue() == map.get(bookName).intValue() && 0 < answer.compareTo(bookName)) {
                answer = bookName;
            }
            //System.out.printf("현재 %s의 개수는 %d개, answer = %s\n", bookName, map.get(bookName), answer);
        }

        System.out.println(answer);
    }
}
