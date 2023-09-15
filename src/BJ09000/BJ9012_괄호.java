package BJ09000;

import java.io.*;

public class BJ9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        char[] str;
        int bracket;
        String canBracket;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            str = br.readLine().toCharArray();

            bracket = 0;
            canBracket = "YES";
            for (int i = 0; i < str.length; i++) {
                if(str[i] == '(') bracket++;
                else if(str[i] == ')') {
                    if(0 < bracket) bracket--;
                    else {
                        canBracket = "NO";
                        break;
                    }
                }
            }

            if(bracket != 0) {
                canBracket = "NO";
            }
            sb.append(canBracket).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
