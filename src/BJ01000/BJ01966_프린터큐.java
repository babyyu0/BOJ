package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01966_프린터큐 {

    private static class Print {
        Print(int important, boolean find) {
            this.important = important; this.find = find;
        }
        int important; boolean find;

        @Override
        public String toString() {
            return "Print{" +
                    "important=" + important +
                    ", find=" + find +
                    '}';
        }
    }
    private static int T, N, M;  // 테스트케이스, 문서의 개수, 문서 위치
    private static Queue<Print> queue;
    private static int[] importants;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        queue = new ArrayDeque<>();
        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            queue.clear();  // 큐 비우기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            importants = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {  // 문서 추가
                importants[i] = Integer.parseInt(st.nextToken());
                queue.add(new Print(importants[i], (i == M)));
            }
            Arrays.sort(importants);
            sb.append(findPrint(0, importants.length - 1)).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static Print curPrint;
    private static int findPrint(int count, int index) {
        while (!queue.isEmpty()) {
            curPrint = queue.poll();  // 문서 뽑기
            if(curPrint.important == importants[index]) {
                count++;
                if(curPrint.find) return count;
                index--;
            } else {
                queue.add(curPrint);
            }
        }

        return -1;
    }
}
