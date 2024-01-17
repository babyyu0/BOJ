package BJ01000;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ01138_한줄로서기 {
    private static class Person {
        public Person(int index, int tall) {
            this.index = index;
            this.tall = tall;
        }

        int index, tall;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] people = new int[N + 1];
        List<Integer> line = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; 0 < i; i--) {
            line.add(people[i], i);
        }

        for (int i = 0; i < N; i++) {
            sb.append(line.get(i)).append(" ");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
