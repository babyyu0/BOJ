package BJ11000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11399_ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[] time = new int[Integer.parseInt(br.readLine())];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < time.length; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(time);

        int sum = time[0];
        for (int i = 1; i < time.length; i++) {
            time[i] += time[i - 1];
            sum += time[i];
        }

        sb.append(sum);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
