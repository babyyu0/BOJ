package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        long sum = 0;
        int[] nums = new int[N];
        int left = 0, count = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
            //System.out.printf("%d부터 %d까지의 SUM = %d\n", left, i, sum);
            while (M <= sum) {
                if(sum == M) {
                    count++;
                    //System.out.printf("%d부터 %d까지의 SUM = %d\n", left, i, sum);
                }
                sum -= nums[left++];
            }
        }
        System.out.println(count);
    }
}
