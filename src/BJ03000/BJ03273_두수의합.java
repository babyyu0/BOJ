package BJ03000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ03273_두수의합 {
    private static class Num {
        public Num(int index, int num) {
            this.index = index;
            this.num = num;
        }

        int index;
        long num;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Num[] nums = new Num[N];
        long answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = new Num(i, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(nums, Comparator.comparingLong(o -> o.num));

        int X = Integer.parseInt(br.readLine());

        int j = N - 1;
        for (int i = 0; nums[i].num <= X && i < j; i++) {
            for (; ; j--) {
                //System.out.printf("(%2d, %2d)의 쌍\n", nums[i].num, nums[j].num);
                if((nums[i].num + nums[j].num) == X) {
                    answer++;
                    j--;
                } else if (X - nums[i].num < nums[j].num) {
                    continue;
                }
                break;
            }
        }

        System.out.println(answer);
    }
}
