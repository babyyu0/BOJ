package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01043_거짓말 {

    private static int N, M;  // 사람의 수 N과 파티의 수 M
    private static int[][] party;
    private static Set<Integer> known;  // 아는 사람 구분

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int P;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        party = new int[M][];

        // 아는 사람 판별
        known = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        for (int i = 0; i < P; i++) {  // 아는 사람 입력
            known.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {  // 파티 입력
            st = new StringTokenizer(br.readLine());
            P = Integer.parseInt(st.nextToken());
            party[i] = new int[P];
            for (int j = 0; j < P; j++) {
                party[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findKnown(0);
        System.out.println(findParty());
    }

    private static void findKnown(int beforeSetSize) {
        boolean flag;
        while (known.size() != beforeSetSize) {
            beforeSetSize = known.size();
            for (int i = 0; i < M; i++) {
                flag = false;  // 아는 사람이 없다고 가정
                for (int j = 0; j < party[i].length; j++) {
                    if(known.contains(party[i][j])) {
                        flag = true;  // 아는 사람이 존재할 경우
                        break;
                    }
                }

                if(!flag) continue;
                for (int j = 0; j < party[i].length; j++) {
                    known.add(party[i][j]);
                }
            }
        }
    }

    private static int findParty() {
        int flag, count = 0;
        for (int i = 0; i < M; i++) {
            flag = 1;
            for (int j = 0; j < party[i].length; j++) {
                if(known.contains(party[i][j])) {
                    flag = 0;
                    break;
                }
            }

            count += flag;
        }

        return count;
    }
}
