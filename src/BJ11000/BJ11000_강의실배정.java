package BJ11000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ11000_강의실배정 {
    private static class Lecture {
        public Lecture(long start, long end) {
            this.start = start;
            this.end = end;
        }

        long start, end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  // 강의 개수
        Lecture[] lectures = new Lecture[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            // 강의 추가
            st = new StringTokenizer(br.readLine());
            lectures[i] = new Lecture(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) return Long.compare(o1.end, o2.end);
            return Long.compare(o1.start, o2.start);
        });  // 시작 시간이 빠른 순 (같다면 종료시간이 빠른 순)으로 오름차순 비교

        PriorityQueue<Long> pq = new PriorityQueue<>();  // 종료 시간을 정렬해 담을 Priority Queue
        pq.add(lectures[0].end);  // 첫 강의의 종료 시간 삽입

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= lectures[i].start) {  // 종료 시간 이후 해당 강의가 시작될 경우
                pq.poll();  // pq 가장 앞의 강의 종료 시간을 해당 강의로 대체
            }
            pq.add(lectures[i].end);  // 강의 종료 시간 삽입
        }

        System.out.println(pq.size());  // 강의실 개수 출력
    }
}
