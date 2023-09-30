package BJ07000;

import java.io.*;
import java.util.*;

public class BJ7662_이중우선순위큐 {
    private static class Num {
        Num(int index, long num) {
            this.index = index; this.num = num;
        }
        int index;
        long num;
    }
    private static int T, k;
    private static long min, max;
    private static PriorityQueue<Num> pq, reversePq;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        pq = new PriorityQueue<>((o1, o2) -> (o1.num < o2.num)? -1: 1);
        reversePq = new PriorityQueue<>((o1, o2) -> (o1.num < o2.num)? 1: -1);

        T = Integer.parseInt(br.readLine());

        String curCommand;
        for (int test_case = 0; test_case < T; test_case++) {
            k = Integer.parseInt(br.readLine());
            visited = new boolean[k];
            for (int i = 0; i < k; i++) {  // 숫자 입력
                st = new StringTokenizer(br.readLine());
                curCommand = st.nextToken();

                switch (curCommand) {
                    case "I":  // 삽입할 경우
                        insert(i, Long.parseLong(st.nextToken()));
                        break;
                    case "D":  // 제거할 경우
                        delete(0 < Integer.parseInt(st.nextToken()));
                        break;
                    default:
                        break;
                }
            }

            try {
                min = print(false);
                max = print(true);
                sb.append(max).append(" ").append(min).append("\n");
            } catch (RuntimeException e) {
                sb.append("EMPTY\n");
            }
            pq.clear();
            reversePq.clear();  // 큐 비웅기
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void insert(int index, long num) {
        pq.add(new Num(index, num));
        reversePq.add(new Num(index, num));
    }

    private static Num curNum;
    private static PriorityQueue<Num> curPq;
    private static long delete(boolean isReverse) {
        if(isReverse) {  // 최대값 제거
            curPq = reversePq;
        } else {
            curPq = pq;
        }
        while(!curPq.isEmpty()) {
            curNum = curPq.poll();
            if(!visited[curNum.index]) {
                visited[curNum.index] = true;
                return curNum.num;
            }
        }

        return -1;
    }
    private static long print(boolean isReverse) {
        if(isReverse) {  // 최대값 제거
            curPq = reversePq;
        } else {
            curPq = pq;
        }
        while(!curPq.isEmpty()) {
            curNum = curPq.poll();
            if(!visited[curNum.index]) {
                // visited[curNum.index] = true;
                return curNum.num;
            }
        }

        throw new RuntimeException();
    }
}
