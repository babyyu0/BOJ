package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ02011_암호코드 {

    private static long[] fiboNums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        if (code.isBlank() || code.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        int codeLen = code.length();
        fiboNums = new long[codeLen + 1];

        long count = 1;
        int start = 0;
        for (int i = 1; i < codeLen; i++) {
            String alpha = "" + code.charAt(i - 1) + code.charAt(i);
            if (code.charAt(i) == '0') {  // 0이 발견된 경우
                if (code.charAt(i - 1) != '1' && code.charAt(i - 1) != '2') {  // 숫자를 만들 수 없는 경우
                    count = 0;
                    break;
                } else {
                    count = (count * fibo(i - 1 - start)) % 1000000;
                    start = i + 1;
                }
            } else if (26 < Integer.parseInt(alpha)) {
                count = (count * fibo(i - start)) % 1000000;
                start = i;
            }
        }
        if (start < codeLen) count = (count * fibo(codeLen - start)) % 1000000;

        System.out.println(count);
    }

    private static long fibo(int i) {
        if (i == 0) return 1;
        if (i <= 2) return fiboNums[i] = i;
        if (0 < fiboNums[i]) return fiboNums[i];

        return fiboNums[i] = (fibo(i - 1) + fibo(i - 2)) % 1000000;
    }
}
