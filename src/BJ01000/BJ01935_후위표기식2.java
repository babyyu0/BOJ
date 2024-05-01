package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;

public class BJ01935_후위표기식2 {
    private static Stack<Character> calStack;
    private static Stack<Double> numStack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        calStack = new Stack<>();
        numStack = new Stack<>();
        int C = Integer.parseInt(br.readLine());
        double[] nums = new double[C];
        String cal = br.readLine();
        for (int i = 0; i < C; i++) {
            nums[i] = Double.parseDouble(br.readLine());
        }
        char ic;
        boolean flag = false;
        for (int i = 0; i < cal.length(); i++) {
            ic = cal.charAt(i);
            if (65 <= ic && ic <= 90) {  // 영단어일 경우
                numStack.push(nums[ic - 65]);
            } else {
                calculation(ic);
                flag = false;
            }
        }

        //while (calculation()) ;

        System.out.printf("%.2f\n", (double) Math.round(numStack.pop() * 100) / 100);
    }

    private static boolean calculation(char cal) {
        //if (calStack.isEmpty()) return false;
        double a = numStack.pop();
        double b = numStack.pop();
        switch (cal) {
            case '+':
                numStack.push(b + a);
                break;
            case '-':
                numStack.push(b - a);
                break;
            case '*':
                numStack.push(b * a);
                break;
            case '/':
                numStack.push(b / a);
                break;
            default:
                return false;
        }
        return true;
    }
}
