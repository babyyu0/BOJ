package BJ01000;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ01005_ACMCraft {
    private static int[] times, sumTimes;
    private static List<Integer>[] rules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int TEST_CASE = Integer.parseInt(br.readLine());  // 테스트케이스 개수
        int N, K, W;  // 건물 개수 N, 건설순서 규칙 개수 K, 찾아야할 건물 W

        int a, b;  // 임의의 변수
        for (int tc = 0; tc < TEST_CASE; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());  // 건물 개수 N
            K = Integer.parseInt(st.nextToken());  // 건설순서 규칙 개수 M

            times = new int[N + 1];  // 시간
            sumTimes = new int[N + 1];  // 도달하는 시간 누적합
            Arrays.fill(sumTimes, -1);  // -1로 채우기 (시간이 0도 있기 때문)
            rules = new ArrayList[N + 1];  // 노드 연결할 List 배열

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());  // 걸리는 시간 저장
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                if (rules[b] == null) rules[b] = new ArrayList<>();
                rules[b].add(a);  // a -> b (b에 가리키는 부모노드 삽입)
            }

            W = Integer.parseInt(br.readLine());  // 지어야 할 건물
            sb.append(calMinTime(W)).append("\n");  // 누적합
            //System.out.println(Arrays.toString(sumTimes));
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int calMinTime(int W) {
        if (0 <= sumTimes[W]) return sumTimes[W];  // 이미 해당 위치 값이 구해져 있을 경우 그 값 return

        int childTime = 0;
        if (rules[W] != null) {
            for (int child : rules[W]) {
                childTime = Math.max(childTime, calMinTime(child));
            }
        }

        return sumTimes[W] = times[W] + childTime;
    }
}
