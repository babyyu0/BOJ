package BJ01000;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 항상 A 노드가 루트 노드
public class BJ01991_트리순회 {
    private static int N;  // 노드의 개수 N
    private static Map<Character, char[]> map;  // 노드의 연관관계 저장할 배열
    private static String[] orders; // preorder, inorder, postorder

    public static void main(String[] args) throws IOException {
        // 초기 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        orders = new String[3];  // preorder, inorder, postorder를 저장할 배열
        Arrays.fill(orders, "");  // 빈 문자열로 초기화

        N = Integer.parseInt(br.readLine().trim());  // 노드 개수 입력받기
        map = new HashMap<>();  // 가운데 노드: { 왼쪽 노드, 오른쪽 노드 }

        char center, left, right;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            center = st.nextToken().charAt(0);
            left = st.nextToken().charAt(0);
            right = st.nextToken().charAt(0);
            if(left != '.' || right != '.') {  // 둘 중 하나가 비어있지 않을 경우
                map.put(center, new char[]{left, right});  // 왼쪽 노드, 오른쪽 노드 가운데 노드에 삽입
            }
        }

        dfs('A');
        sb.append(orders[0]).append("\n")
                .append(orders[1]).append("\n")
                .append(orders[2]).append("\n");
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(char node) {
        orders[0] += node;  // 전위순회 삽입
        if(map.get(node) != null) {
            if(map.get(node)[0] != '.') dfs(map.get(node)[0]);  // 왼쪽 노드 존재 시 왼쪽 노드 탐색
            orders[1] += node;  // 중위 순회 삽입
            if(map.get(node)[1] != '.') dfs(map.get(node)[1]);  // 오른쪽 노드 존재 시 오른쪽 노드 탐색
        } else {
            orders[1] += node;  // 중위 순회 삽입
        }
        orders[2] += node;  // 후위 순회 삽입
    }
}
