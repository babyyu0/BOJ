package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ01059_좋은구간 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int[] arr = new int[L];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int N = Integer.parseInt(br.readLine());
        int MIN = 0, MAX = 1001;

        for (int i = 0; i < L; i++) {
            if(arr[i] < N) {
                MIN = Math.max(arr[i], MIN);
            } else if(arr[i] > N) {
                MAX = Math.min(arr[i], MAX);
            } else {
                System.out.println(0);
                return;
            }
        }

        System.out.println(findRange(MIN + 1, MAX - 1, N));
    }

    private static int findRange(int start, int max, int N) {
        //System.out.println("MIN: " + start + ", MAX: " + max);
        int CNT = 0;
        for (int i = start; i < max && i <= N; i++) {
            CNT += (max - i);
            if(i < N) {
                CNT -= (N - i - 1);
            }
            //System.out.println("CNT: " + CNT);
        }

        return CNT;
    }
}
