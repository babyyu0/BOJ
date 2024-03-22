package BJ01000;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ01475_방번호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<boolean[]> numsList = new ArrayList<>();
        numsList.add(new boolean[10]);
        String roomNum = br.readLine();

        int num;
        boolean flag;
        for (int i = 0; i < roomNum.length(); i++) {
            num = roomNum.charAt(i) - '0';  // 각 자리별 숫자 찾기
            flag = true;
            for (int j = 0; j < numsList.size(); j++) {  // 숫자 세트 반복
                if (!numsList.get(j)[num]) {  // 해당 숫자 사용하지 않았을 경우
                    flag = false;
                    numsList.get(j)[num] = true;  // 숫자 방문 체크
                    break;
                }
                if (num == 9 && !numsList.get(j)[6]) {  // 숫자가 9인데 6을 사용하지 않았을 경우
                    flag = false;
                    numsList.get(j)[6] = true;  // 숫자 방문 체크
                    break;
                } else if (num == 6 && !numsList.get(j)[9]) {  // 숫자가 6인데 9를 사용하지 않았을 경우
                    flag = false;
                    numsList.get(j)[9] = true;  // 숫자 방문 체크
                    break;
                }
            }
            if (flag) {  // 숫자 모두 사용했을 경우
                numsList.add(new boolean[10]);  // 숫자 세트 추가
                numsList.get(numsList.size() - 1)[num] = true;  // 숫자 사용
            }
        }
        System.out.println(numsList.size());
    }
}
