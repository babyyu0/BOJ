package BJ01000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01946_신입사원 {
    private static class Score {
        public Score() {
        }
        public Score(int document, int interview) {
            this.document = document;
            this.interview = interview;
        }

        int document, interview;

        @Override
        public String toString() {
            return "Score{" +
                    "document=" + document +
                    ", interview=" + interview +
                    '}';
        }
    }
    private static final int MAX_CNT = 100000;
    private static int N, MAX_PASS;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        Score[] scores = new Score[MAX_CNT + 1];
        visited = new boolean[MAX_CNT + 1];

        for (int tc = 0; tc < T; tc++) {
            Arrays.fill(visited, false);
            Arrays.fill(scores, new Score());
            N = MAX_PASS = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                //System.out.println("i = " + i);
                st = new StringTokenizer(br.readLine());
                scores[i] = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(scores, (o1, o2) -> Integer.compare(o2.document, o1.document));
            //System.out.println(Arrays.toString(Arrays.copyOf(scores, N)));

            for (int i = N - 2; 0 <= i; i--) {
                if(scores[i].interview > scores[i + 1].interview) {
                    MAX_PASS--;
                    scores[i].interview = Math.min(scores[i].interview, scores[i + 1].interview);
                }
            }

            sb.append(MAX_PASS).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
