package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class BJ01389_케빈베이컨의6단계법칙 {
    private static class Person implements Comparable<Person> {
        Person(int index) {
            this.index = index;
            this.list = new ArrayList<>();
            this.bacon = 0;
        }
        int index;
        List<Integer> list;
        int bacon;

        @Override
        public int compareTo(Person o) {
            return this.bacon - o.bacon;
        }
    }

    private static int N, M;  // 유저의 수, 관계의 수
    private static Person[] people;  // 사람들 관계 리스트
    private static Queue<Integer> queue;
    private static int curPerson;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        queue = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        people = new Person[N + 1];

        int p1, p2;  // 임의 인간 값 삽입
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            p1 = Integer.parseInt(st.nextToken());
            p2 = Integer.parseInt(st.nextToken());

            if(people[p1] == null) people[p1] = new Person(p1);
            if(people[p2] == null) people[p2] = new Person(p2);
            people[p1].list.add(p2);
            people[p2].list.add(p1);
        }

        calBacon();  // 최소 베이컨 계산 함수 호출

        int min = -1;
        for (int i = 1; i <= N; i++) {
            if(people[i] != null && (min == -1 || people[i].bacon < people[min].bacon)) min = i;
        }
        System.out.println(min);
    }

    private static void calBacon() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j) continue;
                people[i].bacon += calRelation(i, j, new int[N + 1]);
            }
        }
    }

    private static int calRelation(int start, int end, int[] visited) {
        queue.clear();
        queue.add(start);
        visited[start] = 1;

        while(!queue.isEmpty()) {
            curPerson = queue.poll();
            for (int i = 0; i < people[curPerson].list.size(); i++) {
                if(people[curPerson].list.get(i) == end) return visited[curPerson];
                if(0 < visited[people[curPerson].list.get(i)]) continue;
                visited[people[curPerson].list.get(i)] = visited[curPerson] + 1;
                queue.add(people[curPerson].list.get(i));
            }
        }
        return 0;
    }
}
