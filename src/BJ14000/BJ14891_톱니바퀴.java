package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ14891_톱니바퀴 {

    private static int[] dirs = {1, 7};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] wheels = new boolean[4][8];  // 톱니바퀴의 S/N극
        int[] pointers = new int[4];  // 톱니바퀴의 맞물리는 구간이 현재 어디를 가리키고 있는지
        Arrays.fill(pointers, 2);  // 처음은 2를 가리키고 있음

        String lines;
        for (int i = 0; i < 4; i++) {
            lines = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheels[i][j] = lines.charAt(j) == '1';
            }
        }

        int K = Integer.parseInt(br.readLine());  // 회전 횟수

        int index, dir, before1, before2, dir1, dir2;
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            index = Integer.parseInt(st.nextToken()) - 1;
            dir = Math.max(Integer.parseInt(st.nextToken()), 0);  // 방향 index
            before1 = pointers[index];
            before2 = pointers[index];
            dir1 = dir;
            dir2 = dir;

            for (int j = index - 1; 0 <= j; j--) {
                before1 = before1 - 4 + ((before1 - 4 < 0) ? 8 : 0);
                if(wheels[j + 1][before1] != wheels[j][pointers[j]]) {  // S극과 N극일 경우
                    before1 = pointers[j];
                    dir1 = (dir1 + 1) % 2;
                    //pointers[j] += dirs[dir1];
                    pointers[j] = (pointers[j] + dirs[dir1]) % 8;
                    //System.out.println("! 톱니바퀴 " + j + "번, " + ((dir1 == 0) ? "반" : "") + "시계방향으로 이동");
                } else {
                    break;
                }
            }

            for (int j = index + 1; j < 4; j++) {
                if(wheels[j - 1][before2] != wheels[j][pointers[j] - 4 + ((pointers[j] - 4 < 0) ? 8 : 0)]) {  // S극과 N극일 경우
                    before2 = pointers[j];
                    dir2 = (dir2 + 1) % 2;
                    //pointers[j] += dirs[dir2];
                    pointers[j] = (pointers[j] + dirs[dir2]) % 8;
                    //System.out.println("! 톱니바퀴 " + j + "번, " + ((dir2 == 0) ? "반" : "") + "시계방향으로 이동");
                } else {
                    break;
                }
            }
            pointers[index] = (pointers[index] + dirs[dir]) % 8;  // 선택 톱니바퀴 방향 바꾸기
        }

        int CNT = 0;
        for (int i = 0; i < 4; i++) {
            pointers[i] = pointers[i] - 2 + ((pointers[i] - 2 < 0) ? 8 : 0);
            CNT += (wheels[i][pointers[i]]) ? (int) Math.pow(2, i) : 0;
            //System.out.println("톱니바퀴 " + i + "번의 12시 방향 극은 " + ((wheels[i][pointers[i]]) ? "S극" : "N극"));
        }

        //System.out.println("CNT: " + CNT);
        System.out.println(CNT);

    }
}
