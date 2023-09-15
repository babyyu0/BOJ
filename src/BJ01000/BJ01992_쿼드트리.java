package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ01992_쿼드트리 {

    private static int N;
    private static String comp;
    private static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new int[N][N];

        String row;
        for(int i = 0; i < N; i++) {
            row = br.readLine();
            for(int j = 0; j < N; j++) {
                tree[i][j] = row.charAt(j) - '0';
            }
        }

        comp = "";

        func(0, 0, N);
        System.out.println(comp);
    }

    private static void func(int x, int y, int size) {
        boolean isComp = true;
        int compNum = tree[x][y];
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(compNum != tree[i][j]) {
                    isComp = false;
                    break;
                }
            }
            if(!isComp) break;
        }
        if(!isComp) {
            comp += "(";
            func(x, y, size / 2);
            func(x, y +  size / 2, size / 2);
            func(x +  size / 2, y, size / 2);
            func(x +  size / 2, y +  size / 2, size / 2);
            comp += ")";
        }
        else {
            comp += tree[x][y];
        }
        return;
    }

}