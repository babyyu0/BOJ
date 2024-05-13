package BJ02000;

import java.io.*;
import java.util.*;

public class BJ02623_음악프로그램 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(st.nextToken()); //가수의 수
		int M = Integer.parseInt(st.nextToken()); //보조 PD의 수
		List<Integer>[] singers = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			singers[i] = new ArrayList<>();
		}
		
		int singer, before = 0;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken()); //i PD가 맡은 가수의 수
			before = 0;
			
			while(0 < j--) {
				singer = Integer.parseInt(st.nextToken()); //n번째 가수				
				if(0 < before) singers[singer].add(before); //더 위에 존재해야 하는 가수 담기
				before = singer; 
			}
		}
		
		int count = 0; boolean flag;
		while(count != N) { //모든 가수 출력할 때까지
			flag = false; //다음 순서가 존재할 수 있는지
			for(int i = 1; i <= N; i++) { // 가수들 순회
				if(singers[i] == null) continue; //이미 처리한 가수면 넘기기
				
				for(int j = 0; j < singers[i].size(); j++) { //i 가수 상위에 존재해야 하는 가수 순회
					if(singers[singers[i].get(j)] == null) { //이미 위에 존재하는 가수
						singers[i].remove(j--); //List에서 제외
					}
				}
				
				if(singers[i] == null || singers[i].size() == 0) { // 상위에 존재해야 하는 가수가 없는 경우 (이미 처리된 경우)
					sb.append(i).append("\n"); //다음 순서로 올 수 있음
					count++; //가수 출력 수 세기
					singers[i] = null; //방문 처리
					flag = true; //가수 처리 완료 체크
				}
			}
			if(!flag) { // 다음 순서가 존재할 수 없을 경우
				System.out.println(0); //0 출력 후 프로그램 종료
				return;
			}
		}
		
		bw.append(sb.toString()); //가수 순서 출력
		bw.flush();
		bw.close();
		br.close();
	}
}
