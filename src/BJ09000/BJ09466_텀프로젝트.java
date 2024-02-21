package BJ09000;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ09466_텀프로젝트 {
    private static int N;
    private static final int MAX = 100000;  // 학생 수 (1부터 시작)
    private static int[] chooses, visited;
    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        stack = new Stack<>();  // 사이클 확인할 Stack
        int IS_NOT_CYCLE;  // 사이클에 속하지 않는 노드를 셀 변수
        chooses = new int[MAX + 1];  // 선택 학생 담을 배열
        visited = new int[MAX + 1];  // 방문 처리 배열

        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        for (int tc = 0; tc < T; tc++) {
            IS_NOT_CYCLE = 0;  // 사이클에 속하지 않는 노드 0으로 초기화
            N = Integer.parseInt(br.readLine());  // 학생 수 (노드 수)
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                chooses[i] = Integer.parseInt(st.nextToken());  // 선택 학생 초기화
                visited[i] = (i == chooses[i]) ? i : 0;  // 방문 배열 초기화 (자기 자신을 가리키는 학생의 경우 자기순환으로 방문 처리)
            }

            for (int i = 1; i <= N; i++) {
                if (visited[i] == 0) {  // 방문하지 않은 노드일 때
                    if (visited[chooses[i]] != 0) {  // 가리키는 노드가 이미 방문한 노드일 때
                        visited[i] = -1;  // 사이클에 속하지 않는 변수임이 확실하므로 -1로 방문 처리 (-1 = 순환하지 않는 노드)
                    } else {  // 가리키는 노드도 방문하지 않았을 때
                        //System.out.println("==== " + i + "번 노드 시작 ====");
                        findCycle(i);  // 노드 방문 함수 호출
                    }
                }
                //System.out.println("방문 여부 : " + Arrays.toString(Arrays.copyOf(visited, N + 1)) + "\n");
                if (visited[i] == -1) IS_NOT_CYCLE++;  // 순환하지 않는 노드일 경우 + 1
            }
            sb.append(IS_NOT_CYCLE).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void findCycle(int start) {  // Stack으로 순환 노드 찾는 함수
        while (0 == visited[start]) {  // 재방문하는 노드가 나올 때까지
            visited[start] = -2;  // 방문 처리 (-2는 현재 함수에서의 임의 방문)
            stack.add(start);  // Stack에 방문 노드 삽입
            start = chooses[start];  // 가리키는 노드로 들어가기
        }

        /*
        System.out.println("이전 Stack : " + stack);
        System.out.println("방문 여부 : " + Arrays.toString(Arrays.copyOf(visited, N + 1)));
        System.out.println("start 값 : " + start);
        System.out.println("visited[start] 값 : " + visited[start]);
         */

        if (visited[start] == -2) {  // 해당 노드에서 재방문 했을 때 (= 사이클 그래프)
            while (!stack.isEmpty() && start != stack.peek()) {  // 재방문 사이클의 첫 노드가 나올 때까지
                visited[stack.pop()] = 1;  // Stack에서 순환하는 노드 방문 처리 (1 = 순환하는 노드)
            }

            if (!stack.isEmpty()) {
                visited[stack.pop()] = 1;  // 재방문 사이클의 첫 노드 방문 처리
            }
        }
        //System.out.println("이후 Stack 변화 : " + stack);

        while (!stack.isEmpty()) {
            visited[stack.pop()] = -1;  // 사이클에 속하지 않는 노드 모두 -1 방문 처리
        }
    }
}
