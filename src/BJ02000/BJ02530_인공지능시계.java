package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02530_인공지능시계 {
    private static int A, B, C, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        D = Integer.parseInt(br.readLine());

        A += (D / 3600);
        D %= 3600;

        B += (D / 60);
        D %= 60;

        C += D;

        B += (C / 60);
        C %= 60;

        A += (B / 60);
        B %= 60;

        A %= 24;

        System.out.println(A + " " + B + " " + C);
    }
}
