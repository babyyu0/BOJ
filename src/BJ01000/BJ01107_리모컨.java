package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class BJ01107_리모컨 {

    private static final int CHANNEL = 100;
    private static int N, M;
    private static String nStr;
    private static boolean[] broke;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if(N == 100) {
            System.out.println(0);
            return;
        }

        M = Integer.parseInt(br.readLine());
        if(M == 0) {
            System.out.println(Integer.toString(N).length());
            return;
        }

        nStr = Integer.toString(N);

        // 고장난 버튼
        broke = new boolean[12];
        StringTokenizer st = new StringTokenizer(br.readLine());
        String btn;
        for (int i = 0; i < M; i++) {
            btn = st.nextToken();
            switch (btn) {
                case "+" :
                    broke[10] = true;
                    break;
                case "-" :
                    broke[11] = true;
                    break;
                default:
                    broke[Integer.parseInt(btn)] = true;
                    break;
            }
        }
        //System.out.println(Arrays.toString(broke));

        int num = moveChannel(0);
        System.out.println(Integer.toString(num).length() + Math.abs(N - num));   // 채널 이동 함수 호출
    }

    private static int moveChannel(int count) {
        while(true) {
            if(!broke[11] && checkChannel(Integer.toString(N + count))) return N + count;
            else if(!broke[10] && checkChannel(Integer.toString(N - count))) return N - count;
            count++;
        }
    }

    private static boolean checkChannel(String num) {
        for (int i = 0; i < num.length(); i++) {
            if(broke[Integer.parseInt(num.substring(i, i + 1))]) return false;
        }
        return true;
    }
}
