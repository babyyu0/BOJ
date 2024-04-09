package BJ14000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ14002_가장긴증가하는부분수열4 {
    private static int[] nums, count;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        nums = new int[N + 1];
        count = new int[N + 1];
        int maxIndex = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            for (int j = i - 1; 0 <= j; j--) {  // 현재 숫자 이전의
                if (nums[j] < nums[i]) {  // 현재 숫자보다 작은
                    count[i] = Math.max(count[j] + 1, count[i]);  // 가장 큰 수열 + 1이 해당 인덱스의 가장 긴 증가하는 수열
                    if (count[maxIndex] < count[i]) {  // 현재까지 가장 긴 수열이라면
                        maxIndex = i;  // 인덱스 저장
                    }
                }
            }
        }
        
        sb.append(count[maxIndex]).append("\n");
        findOrder(maxIndex);  // 가장 긴 수열 위치부터 탐색
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findOrder(int index) {
        if (count[index] == 1) {  // 수열의 첫 시작에 도착했다면
            sb.append(nums[index]).append(" ");  // 현재 인덱스의 숫자 출력
            return;  // 탐색 (완전) 종료
        }
        for (int i = index - 1; 0 < i; i--) {
            if (count[i] == count[index] - 1) { // 직전의 길이 수열이라면
                findOrder(i);  // 해당 인덱스 방문
                sb.append(nums[index]).append(" ");  // 현재 인덱스의 숫자 출력
                return;  // 현재 인덱스 탐색 종료
            }
        }

    }
}
