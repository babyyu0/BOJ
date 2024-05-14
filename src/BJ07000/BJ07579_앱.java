package BJ07000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ07579_앱 {
	private static class App {
		public App(int m, int c) {
			super();
			this.m = m;
			this.c = c;
		}

		int m, c;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //앱 개수
		int M = Integer.parseInt(st.nextToken()); //필요 확보 메모리
		
		App[] apps = new App[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			apps[i] = new App(0, 0);
			apps[i].m = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			apps[i].c = Integer.parseInt(st.nextToken());
		}
		
		int[][] count = new int[N + 1][10001];
		//count[0][0] = 10000001;

		for(int i = 1; i <= N; i++) {
			for(int c = 0; c < apps[i].c; c++) {
				count[i][c] = count[i - 1][c];
			}
			for(int c = apps[i].c; c < 10001; c++) {
				count[i][c] = count[i - 1][c];
				count[i][c] = Math.max(count[i][c], count[i - 1][Math.max(c - apps[i].c, 0)] + apps[i].m);
			}
		}
		
		for(int i = 0; i <= 10000; i++) {
			if(count[N][i] >= M) {
				System.out.println(i);
				return;
			}
		}

		/*int[][] count = new int[N + 1][M + 1];
		Arrays.fill(count[0], 100001);
		count[0][0] = 0;
		
		for(int i = 1; i <= N; i++) {
			//Arrays.fill(count[i], 100001);
			for(int m = 0; m <= M; m++) {
				count[i][m] = Math.min(count[i - 1][m], count[i - 1][Math.max(m - apps[i].m, 0)] + apps[i].c);
			}
		}
		
		System.out.println(count[N][M]);*/
	}
}
