import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ11651_좌표정렬하기2 {
    private static class Pos implements Comparable<Pos> {
        int x;
        int y;

        private Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.y == o.y) return this.x - o.x;
            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        ArrayList<Pos> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        for (int i = 0; i < N; i++) {
            sb.append(list.get(i).x).append(" ").append(list.get(i).y).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
