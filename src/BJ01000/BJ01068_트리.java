package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ01068_트리 {
    private static int N, delete, LEAF_NODE;
    private static List<Integer>[] nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());  // 노드 개수
        int root = 0;  // 루트 노드를 담을 변수
        nodeList = new ArrayList[N];  // 자식 List를 담을 List 배열

        StringTokenizer st = new StringTokenizer(br.readLine());  // 노드 받기
        int parent;  // 해당 노드의 부모 값 담을 임의의 변수 선언
        for (int i = 0; i < N; i++) {
            parent = Integer.parseInt(st.nextToken());  // i 노드가 가리키는 부모 노드
            if(parent < 0) {  // -1일 경우
                root = i;  // 루트 노드 초기화
                continue;
            }

            if(nodeList[parent] == null) nodeList[parent] = new ArrayList<>();
            nodeList[parent].add(i);  // 부모 노드에 자식 노드 담기
        }
        delete = Integer.parseInt(br.readLine());  // 삭제할 노드 변수 초기화
        if (delete == root) {  // 루트 노드가 삭제 되었을 경우
            System.out.println(0);  // 노드의 개수 0
            return;
        }

        dfs(root);  // DFS로 리프노드 탐색
        System.out.println(LEAF_NODE);
    }

    private static void dfs(int node) {
        // 원래 리프노드였거나, 자식 노드 하나가 삭제되어 리프노드가 되었을 때
        if(nodeList[node] == null || (nodeList[node].size() == 1 && nodeList[node].get(0) == delete)) {
            //System.out.println(node + "번 노드는 리프노드.");
            LEAF_NODE++;  // 리프노드 개수 추가
            return;  // 탐색 끝내기
        }

        for (int child : nodeList[node]) {  // 자식 노드 탐색
            if(child == delete) continue;  // 삭제된 노드일 경우 탐색 X
            dfs(child);  // 자식 노드의 자식 탐색 재귀 호출
        }
    }
}
