package BJ09000;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ9019_DSLR {
    private static int T, curIndex, resIndex;
    private static String iStr, resStr;
    private static String[] commands;
    private static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        queue = new ArrayDeque<>();

        T = Integer.parseInt(br.readLine());  // 테스트 케이스

        for (int test_case = 0; test_case < T; test_case++) {
            queue.clear();
            commands = new String[10000];
            st = new StringTokenizer(br.readLine());

            curIndex = Integer.parseInt(st.nextToken());
            commands[curIndex] = ".";
            queue.add(curIndex);
            resIndex = Integer.parseInt(st.nextToken());
            resStr = "0".repeat(4 - Integer.toString(resIndex).length()) + resIndex;

            sb.append(commands[findCommand()].replace(".", "")).append("\n");  // 명령어 찾는 함수 호출
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int tmp;
    private static int findCommand() {
        while(!queue.isEmpty()) {
            curIndex = queue.poll();
            // System.out.println("현재 값 : " + curIndex);

            tmp = doD(curIndex);
            if(tmp == resIndex) {  // D 명령어
                commands[tmp] = commands[curIndex] + "D";
                return tmp;
            } else if (commands[tmp] == null) {
                commands[tmp] = commands[curIndex] + "D";
                queue.add(tmp);
            }

            tmp = doS(curIndex);
            if(tmp == resIndex) {  // S 명령어
                commands[tmp] = commands[curIndex] + "S";
                return tmp;
            } else if (commands[tmp] == null) {
                commands[tmp] = commands[curIndex] + "S";
                queue.add(tmp);
            }

            tmp = doL(curIndex);
            if(tmp == resIndex) {  // L 명령어
                commands[tmp] = commands[curIndex] + "L";
                return tmp;
            } else if (commands[tmp] == null) {
                commands[tmp] = commands[curIndex] + "L";
                queue.add(tmp);
            }

            tmp = doR(curIndex);
            if(tmp == resIndex) {  // R 명령어
                commands[tmp] = commands[curIndex] + "R";
                return tmp;
            } else if (commands[tmp] == null) {
                commands[tmp] = commands[curIndex] + "R";
                queue.add(tmp);
            }
        }

        return -1;
    }

    private static int doD(int i) {
        return (i * 2) % 10000;
    }

    private static int doS(int i) {
        return (i == 0)? 9999 : i - 1;
    }

    private static int doL(int i) {
        iStr = "0".repeat(4 - Integer.toString(i).length()) + i;
        //System.out.println(iStr + "과 " + resStr + "을 비교");

        return Integer.parseInt(iStr.substring(1, iStr.length()) + iStr.substring(0, 1));
    }
    private static int doR(int i) {
        iStr = "0".repeat(4 - Integer.toString(i).length()) + i;
        // System.out.println(iStr + "과 " + resStr + "을 비교");
        return Integer.parseInt(iStr.substring(iStr.length() - 1, iStr.length()) + iStr.substring(0, iStr.length() - 1));
    }
}