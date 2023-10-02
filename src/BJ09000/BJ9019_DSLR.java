package BJ09000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9019_DSLR {
    private static class Logic {
        Logic(int num) { this.num = num; this.command = ""; }
        Logic(int num, String command) { this.num = num; this.command = command; }
        int num;
        String command;
    }
    private static int T, chNum, result;
    private static String LR;
    private static Logic curLogic;
    private static Queue<Logic> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        queue = new ArrayDeque<>();

        T = Integer.parseInt(br.readLine());  // 테스트 케이스

        for (int test_case = 0; test_case < T; test_case++) {
            queue.clear();
            st = new StringTokenizer(br.readLine());
            queue.add(new Logic(Integer.parseInt(st.nextToken())));
            result = Integer.parseInt(st.nextToken());

            sb.append(findDS()).append("\n");  // 명령어 찾는 함수 호출
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String findDS() {
        while(true) {
            curLogic = queue.poll();
            LR = findLR(Integer.toString(curLogic.num), Integer.toString(result), "", "");
            if(LR != null) {
                return curLogic.command + LR;
            }

            chNum = doD(curLogic.num);
            if(compare(chNum)) {
                return curLogic.command + "D";
            } else {
                queue.add(new Logic(chNum, curLogic.command + "D"));
            }

            chNum = doS(curLogic.num);
            if(compare(chNum)) {
                return curLogic.command + "S";
            } else {
                queue.add(new Logic(chNum, curLogic.command + "S"));
            }
        }
    }

    private static String tmp;
    private static String findLR(String num, String result, String left, String right) {
        if(!isLR(num, result, 0)) return null;

        tmp = num;
        while(!tmp.equals(result)) {
            System.out.println(tmp);
            tmp = Integer.toString(doL(tmp));
            left += "L";
        }

        tmp = num;
        while(!tmp.equals(result)) {
            System.out.println(tmp);
            tmp = Integer.toString(doR(tmp));
            right += "R";
        }

        return (left.length() < right.length())? left : right;
    }

    private static boolean[] visited;
    private static boolean isLR(String num, String result, int cnt) {
        if(num.length() != result.length()) return false;

        visited = new boolean[num.length()];
        for (int i = 0; i < num.length(); i++) {
            for (int j = 0; j < result.length(); j++) {
                if(!visited[j] && num.charAt(i) == result.charAt(j)) {
                    cnt++;
                    visited[j] = true; break;
                }
            }
        }

        return cnt == num.length();
    }

    private static int doD(long num) {
        return (int) (num * 2L % 10000L);
    }

    private static int doS(int num) {
        return (num != 0)? num - 1 : 9999;
    }

    private static int doL(String num) {
        return Integer.parseInt(num.substring(1, num.length()) + num.substring(0, 1));
    }
    private static int doR(String num) {
        return Integer.parseInt(num.substring(num.length() - 1, num.length()) + num.substring(0, num.length() - 1));
    }

    private static boolean compare(int num) {
        return result == num;
    }
}
