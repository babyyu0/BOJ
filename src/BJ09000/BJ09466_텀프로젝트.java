package BJ09000;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ09466_텀프로젝트 {
    private static int N, MAX_STUDENT;  // 학생 수 (1부터 시작)
    private static int[] chooses, parents, isSolo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            N = MAX_STUDENT = Integer.parseInt(br.readLine());
            chooses = new int[N + 1];
            parents = new int[N + 1];
            isSolo = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                chooses[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println("선택 학생 : " + Arrays.toString(chooses));

            for (int i = 1; i <= N; i++) {
                if(parents[i] == 0) dfs(chooses[i], i);
                //System.out.println("순환 학생 : " + Arrays.toString(parents));
                //System.out.println("isSolo : " + Arrays.toString(isSolo));
                MAX_STUDENT -= isSolo[i];
            }
            sb.append(MAX_STUDENT).append("\n");
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int child, int parent) {
        if(parents[child] == parent) {
            //System.out.println("== 순환 빠져나가기 (parents[" + child + "] == " + parent + " ) ==");
            isSolo[child] = 1;
            return;
        }
        parents[child] = parent;
        dfs(chooses[child], child);
        if(parents[child] == parent) {
            //System.out.println("== 순환 값 변경 (parents[" + child + "] == " + parent + " ) ==");
            isSolo[child] = 1;
            return;
        } else {
            parents[parent] = -1;
        }
    }
}
