package BJ11000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11650_좌표정렬하기 {

    static class Pos implements Comparable<Pos> {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.r != o.r) return this.r - o.r;
            else return this.c - o.c;
        }

        @Override
        public String toString() {
            return this.r + " " + this.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());  // 좌표 개수
        Pos[] arr = new Pos[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
