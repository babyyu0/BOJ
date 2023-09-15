package BJ13000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ13305_주유소 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] distance = new long[N - 1];
        long[] gasQuantity = new long[N];

        // 필요한 휘발유 측정
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }

        // 주유 가격 측정
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gasQuantity[i] = Long.parseLong(st.nextToken());
        }

        long sum = 0;
        int move;
        for (int i = 0; i < N - 1; i++) {
            move = 0;
            sum += distance[i] * gasQuantity[i];
            // System.out.println("필요 기름 : " + distance[i]);
            // System.out.println("주유 값 : " + (distance[i] * gasQuantity[i]));

            while(i + ++move < (N - 1) && gasQuantity[i] < gasQuantity[i + move]) {
                sum += distance[i + move] * gasQuantity[i];
                // System.out.println("주유 값 : " + (distance[i + move] * gasQuantity[i]));
            }
            i += (move - 1);
            // System.out.println(sum);
        }
        sb.append(sum);
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
