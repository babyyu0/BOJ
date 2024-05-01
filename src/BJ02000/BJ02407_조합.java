package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BJ02407_조합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        BigInteger N = new BigInteger(st.nextToken());
        BigInteger M = new BigInteger(st.nextToken());
        System.out.println(fact(N, N.subtract(M)).divide(fact(M, new BigInteger("1"))));
    }

    private static BigInteger fact(BigInteger n, BigInteger last) {
        if(n.equals(last)) return new BigInteger("1");
        return n.multiply(fact(n.subtract(new BigInteger("1")), last));
    }
}
