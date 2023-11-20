package BJ01000;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ01015_수열정렬 {
    private static class Num {
        Num(int sortedIndex, int realIndex, int num) {
            this.sortedIndex = sortedIndex;
            this.realIndex = realIndex;
            this.num = num;
        }
        int sortedIndex, realIndex, num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Num[] num = new Num[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = new Num(0, i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(num, (o1, o2) -> {
            if(o1.num != o2.num) {
                return Integer.compare(o1.num, o2.num);
            }
            return Integer.compare(o1.realIndex, o2.realIndex);
        });
        for (int i = 0; i < N; i++) {
            num[i].sortedIndex = i;
        }

        Arrays.sort(num, Comparator.comparingInt(o -> o.realIndex));

        for (int i = 0; i < N; i++) {
            sb.append(num[i].sortedIndex).append(" ");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
