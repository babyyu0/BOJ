package BJ10000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10799_쇠막대기 {
    private static String str;  // 괄호를 입력받을 문자열 변수
    private static int stick, cnt;  // 막대기 개수, counting된 막대기 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().trim();  // 괄호 문자열 초기화

        // 레이저인지 막대기인지 판별하는 boolean 변수
        // true: 레이저, false: 막대기
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '(':
                    stick++;  // 막대기 개수 늘리기
                    flag = true;
                    break;
                case ')':
                    stick--;  // 막대기의 끝 or 레이저이므로 막대기 개수 줄이기
                    if(flag) {  // 레이저일 경우
                        cnt += stick;  // 잘린 막대기 개수 더하기
                    } else {
                        cnt++;
                    }
                    flag = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println(cnt);
    }
}
