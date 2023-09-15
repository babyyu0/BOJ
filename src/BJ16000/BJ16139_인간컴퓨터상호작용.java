package BJ16000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ16139_인간컴퓨터상호작용 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        char[] str = br.readLine().toCharArray();  // 문자열 받기
        long[][] alphabetCnt = new long[26][str.length];
        for (int i = 0; i < str.length; i++) {
            System.out.print(str[i] + " ");
            for (int j = 0; j < alphabetCnt.length; j++) {
                alphabetCnt[j][i] = alphabetCnt[j][i - 1];
            }
            alphabetCnt[str[i] - '0' - 97][i]++;
        }
        for (int i = 0; i < 26; i++) {
            System.out.println(Arrays.toString(alphabetCnt[i]));
        }
    }
}