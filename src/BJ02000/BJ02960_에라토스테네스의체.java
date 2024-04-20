package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ02960_에라토스테네스의체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] numList = new int[N + 1];
        int erase = 0;
        for (int i = 2; i <= N; i++) {
            numList[i] = i;
        }

        for (int i = 2; i <= N; i++) {
            if(isPrime(numList[i])) {  // 소수 중 가장 작은 수일 경우
                for (int j = i; j <= N; j += i) {
                    if(numList[j] == 0) continue;
                    //System.out.println(numList[j] + " 지우기");
                    if(++erase == K) {
                        System.out.println(numList[j]);
                        return;
                    }
                    numList[j] = 0;
                }
            }
        }
    }

    private static boolean isPrime(int N) {
        if(N == 0) return false;
        for (int i = 2; i < N; i++) {
            if(N % i == 0) return false;
        }
        return true;
    }
}
