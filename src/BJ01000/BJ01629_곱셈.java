package BJ01000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ01629_곱셈 {

    private static long NUM, CNT, MOD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        NUM = Integer.parseInt(st.nextToken());
        CNT = Integer.parseInt(st.nextToken());
        MOD = Integer.parseInt(st.nextToken());

        sb.append(findMod(CNT));

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static long findMod(long cnt) {
        if(cnt == 0) {
            return 1;
        }

        if(cnt % 2 == 0) {
            long tmp = findMod(cnt / 2);
            return (tmp * tmp) % MOD;
        }
        return (NUM * findMod(cnt - 1)) % MOD;
    }
}
