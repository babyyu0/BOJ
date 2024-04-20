package BJ10000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10825_국영수 {
    private static class Student {
        public Student(String name, int krScore, int enScore, int maScore) {
            this.name = name;
            this.krScore = krScore;
            this.enScore = enScore;
            this.maScore = maScore;
        }

        String name;
        int krScore, enScore, maScore;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Student[] students = new Student[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(students, (o1, o2) -> {
            if (o1.krScore != o2.krScore) return Integer.compare(o2.krScore, o1.krScore);
            else if (o1.enScore != o2.enScore) return Integer.compare(o1.enScore, o2.enScore);
            else if (o1.maScore != o2.maScore) return Integer.compare(o2.maScore, o1.maScore);
            else return o1.name.compareTo(o2.name);
        });
        for (int i = 0; i < N; i++) {
            sb.append(students[i].name).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
