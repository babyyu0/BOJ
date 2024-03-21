package BJ02000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ02467_용액 {
    private static int N;
    private static long MIN_DIFF;  // 전체 용액의 수
    private static long[] nums;  // 용액 수
    private static long[] answer;

    public static void main(String[] args) throws IOException {
        MIN_DIFF = Long.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new long[N];
        answer = new long[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        int compIndex;
        for (int i = 0; i < N; i++) {
            compIndex = findMinDiff(i + 1, N - 1, i);
            if (Math.abs(nums[i] + nums[compIndex]) < MIN_DIFF && compIndex != i) {
                MIN_DIFF = Math.abs(nums[i] + nums[compIndex]);
                answer[0] = nums[i];
                answer[1] = nums[compIndex];
                if (MIN_DIFF == 0) break;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static int findMinDiff(int left, int right, int answer) {
        if (N <= right || N <= left) {
            return right;
        } else if (left < 0) {
            return left;
        } else if (left + 1 >= right) {
            return Math.abs(nums[left] - (-nums[answer])) < Math.abs(nums[right] - (-nums[answer])) ? left : right;
        }

        int mid = (left + right) / 2;
        if (mid == answer) {
            return findMinDiff(left + 1, right, answer);
        }

        if (nums[mid] < -nums[answer]) {
            return findMinDiff(mid, right, answer);
        } else if (-nums[answer] < nums[mid]) {
            return findMinDiff(left, mid, answer);
        }

        return mid;
    }
}
