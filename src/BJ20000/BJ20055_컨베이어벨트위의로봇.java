package BJ20000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ20055_컨베이어벨트위의로봇 {
    private static class Conveyor {
        public Conveyor(int durability, boolean robotIn) {
            this.durability = durability;
            this.robotIn = robotIn;
        }

        int durability;
        boolean robotIn;
        public Conveyor clone() {
            return new Conveyor(this.durability, this.robotIn);
        }
    }
    private static int N, DUR;
    private static Conveyor[] conveyors;
    private static List<Conveyor> conveyorList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        conveyors = new Conveyor[N * 2];
        conveyorList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            //conveyors[i] = new Conveyor(Integer.parseInt(st.nextToken()), false);
            conveyorList.add(new Conveyor(Integer.parseInt(st.nextToken()), false));
            if(conveyorList.get(i).durability == 0) DUR++;
        }
        if(K <= DUR) {
            System.out.println(1);
            return;
        }

        int order = 0;
        //System.out.println("0. 첫 컨테이너...");
        //printMap();
        while(DUR < K) {
            order++;
            // 각 단계 시작
            //System.out.println("1. 컨테이너 돌리기...");
            turnConvayors();  // 컨테이너 돌리기
            //printMap();
            conveyorList.get(N - 1).robotIn = false;  // 내리는 위치 로봇 내리기

            //System.out.println("2. 로봇 움직이기...");
            moveRobot();  // 로봇 움직이기
            //printMap();
            conveyorList.get(N - 1).robotIn = false;  // 내리는 위치 로봇 내리기


            //System.out.println("3. 로봇 올리기...");
            if(conveyorList.get(0).durability != 0) {  // 올리는 위치 로봇 올리기
                conveyorList.get(0).robotIn = true;
                conveyorList.get(0).durability--;
                if(conveyorList.get(0).durability == 0) DUR++;
            }
            //printMap();
        }
        System.out.println(order);
    }
    private static void turnConvayors() {
        conveyorList.add(0, conveyorList.remove(2 * N - 1));
    }
    private static void moveRobot() {
        for (int i = N * 2 - 1; 0 < i; i--) {
            // 로봇이 다음 칸으로 이동 가능할 때
            if(!conveyorList.get(i).robotIn && 0 < conveyorList.get(i).durability) {
                conveyorList.get(i).robotIn = conveyorList.get(i - 1).robotIn;
                conveyorList.get(i - 1).robotIn = false;
                // 로봇이 움직인 칸이 내구도가 0이 되었다면
                if(conveyorList.get(i).robotIn && --conveyorList.get(i).durability == 0) {
                    DUR++;
                }
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            if(conveyorList.get(i).robotIn) System.out.printf("%2d:%2s ", conveyorList.get(i).durability, "로봇");
            else System.out.printf("%2d:%2s ", conveyorList.get(i).durability, "-");
        }
        System.out.println();
        for (int i = N - 1; 0 <= i; i--) {
            if(conveyorList.get(N + i).robotIn) System.out.printf("%2d:%2s ", conveyorList.get(N + i).durability, "로봇");
            else System.out.printf("%2d:%2s ", conveyorList.get(N + i).durability, "-");
        }
        System.out.println("\n");
    }
}
