package BJ10000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ10814_나이순정렬 {

    private static class Person implements Comparable<Person> {
        int index;
        int age;
        String name;

        public Person(int index, int age, String name) {
            this.index = index;
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            if(this.age != o.age) return this.age - o.age;
            else return this.index - o.index;
        }

        @Override
        public String toString() {
            return this.age + " " + this.name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Person[] people = new Person[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            people[i] = new Person(i, Integer.parseInt(st.nextToken()), st.nextToken().trim());
        }

        Arrays.sort(people);
        for (int i = 0; i < N; i++) {
            sb.append(people[i]).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
