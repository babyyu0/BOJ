package BJ14000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();  // 블록의 최대 높이
        int W = Integer.parseInt(st.nextToken());  // 블록 정렬 개수 (너비)

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 높이를 비교할 Priority Queue 생성

        int blockHeight, maxHeight = 0;  // 현재 확인할 블록 높이, 지금까지 찾은 블록 중 최대 높이
        int count = 0;  // 지금까지 채운 물의 칸 수
        for (int i = 0; i < W; i++) {
            blockHeight = Integer.parseInt(st.nextToken());  // 다음 블록 높이 받기
            if (maxHeight <= blockHeight) {  // 현재 블록이 최대 높이와 같거나 최대 높이보다 클 경우
                while (!pq.isEmpty() && pq.peek() <= maxHeight) {  // 지금까지의 낮은 높이들을
                    count += maxHeight - pq.poll();  // 이전의 최대 높이만큼 채우기
                }
                maxHeight = blockHeight;  // 최대 높이를 현재 블록 높이로 변경 (이전 블록은 다시 돌아보지 않음)
            } else {  // 현재 블록이 최대 높이보다 낮을 경우
                while (!pq.isEmpty() && pq.peek() < blockHeight) {  // 현재 높이보다 낮은 높이가 있을 때
                    count += blockHeight - pq.poll();  // 블록 높이를 현재 높이로 채우기
                    pq.add(blockHeight);  // 물 더하기
                }
            }
            pq.add(blockHeight);  // 현재 블록 삽입
            System.out.println(pq);
        }

        System.out.println(count);
    }
}
