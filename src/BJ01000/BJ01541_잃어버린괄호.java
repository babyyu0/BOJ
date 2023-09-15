package BJ01000;

import java.io.*;

public class BJ01541_잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split("-");
        String[] splitStr;

        int sum = 0;
        splitStr = str[0].split("\\+");
        for (int j = 0; j < splitStr.length; j++) {
            sum += Integer.parseInt(splitStr[j]);
        }

        for (int i = 1; i < str.length; i++) {
            splitStr = str[i].split("\\+");
            for (int j = 0; j < splitStr.length; j++) {
                sum -= Integer.parseInt(splitStr[j]);
            }
        }

        sb.append(sum);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
