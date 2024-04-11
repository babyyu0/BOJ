package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ01976_여행가자 {
    private static int N, M;  // 도시 수, 여행계획에 있는 도시 수
    private static int[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        nodes = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = i;
        }

        StringTokenizer st;
        int aParent, bParent;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if(st.nextToken().equals("1")) {
                    aParent =  parent(i);
                    bParent = parent(j);
                    if(aParent < bParent) nodes[bParent] = aParent;
                    else if (bParent < aParent) nodes[aParent] = bParent;
                }
            }
        }
        //System.out.println(Arrays.toString(nodes));

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        for (int i = 1; i < M; i++) {
            if(parent(a) != parent(a = Integer.parseInt(st.nextToken()))) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }

    private static int parent(int child) {
        if(nodes[child] == child) {
            return child;
        }

        return nodes[child] = parent(nodes[child]);
    }
}
