package BJ04000;

import java.io.*;
import java.util.StringTokenizer;

public class BJ4589_GnomeSequencing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[3]; boolean flag;
        sb.append("Gnomes:\n");
        for (int test_case = 0; test_case < N; test_case++) {
            flag = false;
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 3; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            if(nums[0] < nums[1]) { // 오름차순
                flag = (nums[1] < nums[2]);
            } else if(nums[0] > nums[1]) { // 오름차순
                flag = (nums[1] > nums[2]);
            }

            sb.append(flag ? "Ordered" : "Unordered").append("\n");
        }

        bw.append(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
