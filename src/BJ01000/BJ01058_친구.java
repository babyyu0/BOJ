package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ01058_친구 {
    private static int N;  // 사람의 수
    private static boolean[][] isFriend;
    private static boolean[][] newFriend;
    private static int[] friendsCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isFriend = new boolean[N][N];
        newFriend = new boolean[N][N];
        friendsCnt = new int[N];

        String s;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < N; j++) {
                isFriend[i][j] = s.charAt(j) == 'Y';
                newFriend[i][j] = isFriend[i][j];
                friendsCnt[i] += isFriend[i][j] ? 1 : 0;
            }
        }
//        System.out.println(Arrays.deepToString(isFriend));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(isFriend[i][j]) {  // 둘이 친구일 경우
                    for (int k = 0; k < N; k++) {
                        if(isFriend[j][k] && !newFriend[i][k] && i != k) {
                            newFriend[i][k] = true;
                            friendsCnt[i]++;
                        }
                    }
                }
            }
        }

        Arrays.sort(friendsCnt);

        System.out.println(friendsCnt[N - 1]);
    }
}
