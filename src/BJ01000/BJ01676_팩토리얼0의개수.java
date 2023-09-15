import java.io.*;
import java.math.BigInteger;

public class BJ01676_팩토리얼0의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 변수 N 받기
        int N = Integer.parseInt(br.readLine());

        // 팩토리얼 계산
        BigInteger fact = BigInteger.valueOf(1);
        for (int i = N; i > 0; i--) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }

        String factStr = fact.toString();
        int cntZero = 0; // 0의 개수

        for (int i = factStr.length() - 1; i >= 0; i--) {
            if(factStr.charAt(i) != '0') break;
            cntZero++;
        }

        System.out.println(cntZero);
    }
}
