package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BJ01235_학생번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] studentNum = new String[N];
        for (int i = 0; i < N; i++) {
            studentNum[i] = br.readLine();
        }

        int len = studentNum[0].length();
        Set<String> set = new HashSet<>();
        String subStr;
        for (int i = 0; i <= studentNum[0].length(); i++) {
            for (int j = 0; j < N; j++) {
                subStr = studentNum[j].substring(len - i, len);
                if(set.contains(subStr)) {
                    set.clear();
                    break;
                } else {
                    set.add(subStr);
                }
            }
            if(set.size() == N) {
                System.out.println(i);
                return;
            }
        }
    }
}
