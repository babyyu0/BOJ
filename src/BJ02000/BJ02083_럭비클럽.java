package BJ02000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ02083_럭비클럽 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String name; int age, weight;
        while(true) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            age = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            if(name.equals("#")) break;
            sb.append(name).append(" ");
            if(17 < age || 80 <= weight) {
                sb.append("Senior");
            } else {
                sb.append("Junior");
            }
            sb.append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
