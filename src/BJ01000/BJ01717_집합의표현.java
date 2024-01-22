package BJ01000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ01717_집합의표현 {
    private static int[] set;  // 가리키는 우두머리 원소를 삽입할 배열 선언
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  // 초기 집합 개수
        int M = Integer.parseInt(st.nextToken());  // 주어지는 연산 개수
        set = new int[N + 1];  // 초기 집합 개수만큼 초기화

        for (int i = 1; i <= N; i++) {
            set[i] = i;
        }

        int s;  // 집합 연산 구분할 변수 선언
        int a, b;  // 원소 두 개 삽입할 변수 선언
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());  // s a b 입력 받기
            s = Integer.parseInt(st.nextToken());  // 0(합집합) / 1(확인) 입력 받기
            a = Integer.parseInt(st.nextToken());  // 첫 번째 원소 입력 받기
            b = Integer.parseInt(st.nextToken());  // 두 번째 원소 입력 받기
            
            switch (s) {
                case 0:  // 합집합일 경우
                    union(a, b);
                    break;
                    
                case 1:  // 확인일 경우
                    sb.append(check(a, b)).append("\n");
                    break;
                    
                default:
                    break;
                    
            }
            //System.out.println(Arrays.toString(set));
        }  // 반복

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if(aParent <  bParent) {
            set[b] = aParent;
            set[bParent] = aParent;
        } else {
            set[a] = bParent;
            set[aParent] = bParent;
        }
    }

    private static String check(int a, int b) {
        return find(a) == find(b) ? "YES" : "NO";
    }

    private static int find(int n) {
        if(n == set[n]) return n;
        return set[n] = find(set[n]);
    }
}
