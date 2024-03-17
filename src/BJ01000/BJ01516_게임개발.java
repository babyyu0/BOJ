package BJ01000;

import java.io.*;
import java.util.*;

public class BJ01516_게임개발 {
    private static int N;
    private static int[] times, dp;
    private static List<Integer>[] parentList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        times = new int[N + 1];
        dp = new int[N + 1];
        parentList = new ArrayList[N + 1];

        int parent;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            parentList[i] = new ArrayList<>();
            while (0 < (parent = Integer.parseInt(st.nextToken()))) {  // -1이 나오기 전까지
                parentList[i].add(parent);  // 부모 노드에 해당 노드 추가
            }

            if (parentList[i].isEmpty()) {  // 루트 노드의 경우
                dp[i] = times[i];  // 건물 짓는 시간이 모두 지어지는 시간
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dp[i] == 0) {  // 해당 건물 짓는 시간이 계산되어있지 않는 경우
                findRoad(i);  // 계산
            }
            sb.append(dp[i]).append("\n");
        }

        bw.append(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findRoad(int node) {
        int tmp = 0;
        if (parentList[node] != null) {  // 루트 노드가 아닐 경우
            for (int parent : parentList[node]) {  // 부모 노드 탐색
                if (0 < dp[parent]) tmp = Math.max(tmp, dp[parent]);  // 해당 노드까지 짓는 시간이 이미 계산되어 있을 경우
                else tmp = Math.max(tmp, findRoad(parent));  // 계산되어있지 않은 경우 이 노드도 함께 계산
            }
        }

        return dp[node] = tmp + times[node];  // 건물짓는 시간 최종 계산
    }
}