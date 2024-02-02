package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01021_회전하는큐 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] picked = new int[m];
        int[] visited = new int[n + 1];
        Arrays.fill(visited, 1);

        st = new StringTokenizer(br.readLine());
        int current = 1, move = 0, m1, m2, c1, c2;
        for (int i = 0; i < m; i++) {
            picked[i] = Integer.parseInt(st.nextToken());

            m1 = m2 = 0;
            c1 = c2 = current;

            while(c1 != picked[i]) {
                c1++;
                c1 = (c1 <= n) ? c1 : 1;
                m1 += visited[c1];
            }

            while(c2 != picked[i]) {
                c2--;
                c2 = (0 < c2) ? c2 : n;
                m2 += visited[c2];
            }

            //System.out.println("시작점 " + current + "에서" + Math.min(m1, m2) + "만큼 이동");
            move += Math.min(m1, m2);

            current = picked[i] + 1;
            current = (current <= n) ? current : 1;
            while(visited[current] == 0) {
                current++;
                current = (current <= n) ? current : 1;
            }

            visited[picked[i]] = 0;
            //System.out.println("move: " + move);
        }

        System.out.println(move);
    }
}
