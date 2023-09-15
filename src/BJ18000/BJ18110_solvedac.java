package BJ18000;

import java.io.*;
import java.util.Arrays;

public class BJ18110_solvedac {

    private static long sum;  // 난이도 의견 개수, 합친 값
    private static double n;  // 난이도 개수
    private static int rmAver;  // 절사평균 값
    private static int[] numArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Double.parseDouble(br.readLine());
        rmAver = (int) Math.round((n / 10.0) * 1.5);
        numArray = new int[(int) n];

        for (int i = 0; i < n; i++) {
            numArray[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(numArray);

        for (int i = rmAver; i < n - rmAver; i++) {
            sum += numArray[i];
        }


        System.out.println(Math.round(sum / (n - rmAver * 2.0)));
    }
}
