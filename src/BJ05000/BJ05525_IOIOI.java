package BJ05000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ05525_IOIOI {
    private static int N, M, findS;
    private static String S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) - 1;
        M = Integer.parseInt(br.readLine());
        S = br.readLine();
//        N = 5;  // IOIOIOIOIOI
//        M = 10;  // IOIOIOIOIOIOIOIOIOIOI
//        S = "IO".repeat(5);
//        System.out.println(S);

        int count;
        for (int i = 0; i < M; i++) {
            if(i + 3 <= M && S.substring(i, i + 3).equals("IOI")) {
                count = 0;
                while(i + 3 <= M && S.substring(i, i + 3).equals("IOI")) {
                    //System.out.println(i + "번째에서" + S.substring(i, i + 3) + "뽑기");
                    i += 2;
                    count++;
                }
                // System.out.println("총 " + count + "개");
                findS += (0 < count - N)? (count - N) : 0;
            }
        }

        System.out.println(findS);
    }
}
