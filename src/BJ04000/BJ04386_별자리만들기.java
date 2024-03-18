package BJ04000;

import java.io.*;
import java.util.*;

public class BJ04386_별자리만들기 {

    private static class Dist {
        public Dist(int index1, int index2, double dist) {
            this.index1 = index1;
            this.index2 = index2;
            this.dist = dist;
        }
        int index1, index2;  // 두 노드
        double dist;  // 두 노드를 잇는 간선의 가중치
    }

    private static int N;
    private static int[] roots;  // 노드의 루트노드 삽입할 배열
    private static List<Dist> distList;  // 간선 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        double[][] stars = new double[N][2];  // 각 별의 위치 담을 2차원 배열
        roots = new int[N];
        Arrays.fill(roots, -1);  // 처음에는 루트 노드가 없다고 정의
        distList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        stars[0][0] = Double.parseDouble(st.nextToken());
        stars[0][1] = Double.parseDouble(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());

            for (int j = 0; j < i; j++) {  // 각 간선의 가중치 담기 (무방향)
                distList.add(new Dist(j, i, Math.sqrt(Math.pow(stars[j][0] - stars[i][0], 2) + Math.pow(stars[j][1] - stars[i][1], 2))));
            }
        }
        distList.sort(Comparator.comparingDouble(o -> o.dist));  // 간선의 가중치가 작은 순으로 정렬
        System.out.println(findShortestLine());  // 최소 신장 출력
    }

    private static double findShortestLine() {
        double sum = 0;  // 가중치의 합을 담을 변수 선언
        int maxRoot, minRoot;  // 루트 노드를 변경하기 위한 변수 선언

        for (Dist dist : distList) {
            if (roots[dist.index1] == -1 && roots[dist.index2] == -1) {  // 두 노드 모두 루트가 없을 경우
                roots[dist.index2] = roots[dist.index1] = Math.min(dist.index1, dist.index2);  // 인덱스가 더 작은 노드를 루트로 설정
            } else if (roots[dist.index1] == roots[dist.index2]) {  // 사이클이 생성될 경우
                continue;  // 연결하지 않음 (가중치 합 X)
            } else if (roots[dist.index1] == -1 || roots[dist.index2] == -1) {  // 둘 중 하나의 노드가 루트가 없을 경우
                roots[dist.index1] = roots[dist.index2] = Math.max(roots[dist.index1], roots[dist.index2]);  // 루트 공유
            } else {
                minRoot = Math.min(roots[dist.index1], roots[dist.index2]);  // 인덱스가 더 작은 루트 노드를 최종 루트로 설정
                maxRoot = Math.max(roots[dist.index1], roots[dist.index2]);  // 인덱스가 더 큰 루트 확인

                for (int i = 0; i < N; i++) {
                    roots[i] = (roots[i] == maxRoot) ? minRoot : roots[i];  // 인덱스가 더 큰 루트를 최종 루트의 자식으로 변경
                }
            }

            sum += dist.dist;  // 해당 간선 가중치 합하기
        }
        return Math.round(sum * 100.0) / 100.0;  // 소수점 2자리 수에서 반올림
    }
}
