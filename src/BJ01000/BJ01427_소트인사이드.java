package BJ01000;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BJ01427_소트인사이드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            numList.add(str.charAt(i) - '0');
        }

        Collections.sort(numList, Comparator.reverseOrder());
        for (int i = 0; i < numList.size(); i++) {
            sb.append(numList.get(i));
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
