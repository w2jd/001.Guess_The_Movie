package com.company;

import java.util.Arrays;
import java.util.Scanner;


/*
- Question
        K 교수님은 랜덤하게 출석을 호명하신다. 특히 한 수업 시간에 여러 번 호명할 때도 있는데, 아무래도 어떤 사람은 여러 번 볼리고 어떤 사람은 아예 호명이 되지 않는 것 같다. 한렬이는 호명된 리스트에서 아예 출석이 한 번도 안 불려진 사람을 찾는 프로그램을 짜보려고 한다.

        입력:
        인원 N, 호명 회수 K  (0 < N < 100, 0 <= K <= 10)
        K줄의 출석 호명 번호 (1~N 사이의 번호를 랜덤하게 나열, 0으로 끝남)
        한 번에 부르는 수는 N명을 넘지 않는다.

        출력:
        1~N번 중에 한 번도 호명되지 않은 번호의 개수를 출력한다.

        예시1:
        10명(1~10번)의 학생이 있고 출석은 2번 불렀다. 한 번도 안 불려진 번호는 7번과 10번이므로 답은 2이다.
        10 2
        4 2 6 5 8 6 0
        1 2 9 3 8 4 5 0
*/

public class _006_Call_Student {
    public static void main() {
        Input inputByUser = new Input();
        ProcessCheck processCheck = new ProcessCheck();

        inputByUser.inputInitialData();
        inputByUser.callToinputCallData();
        processCheck.checkXO(inputByUser.getNumberOfHuman(), inputByUser.getStudent());
    }
}

// 사용자 입력 처리
class Input {
    private int numberOfHuman;
    private int countCall;
    private int[] student;
    private Scanner scan = new Scanner(System.in);

    Input() {
        numberOfHuman = 0;
        countCall = 0;
    }

    int getNumberOfHuman() {
        return numberOfHuman;
    }

    int[] getStudent() {
        return student;
    }

    // 초기 값 입력받음 : 인원, 호명 횟수
    void inputInitialData() throws NumberFormatException {
//        System.out.print("Input (인원 호명횟수) : ");
        String[] input = scan.nextLine().split(" ");
        numberOfHuman = Integer.parseInt(input[0]);
        countCall = Integer.parseInt(input[1]);

        student = new int[numberOfHuman+1];
        Arrays.fill(student, 0);
    }

    // 호명 횟수만큼 inputCallData 실행
    void callToinputCallData() {
        for(int cnt=0; cnt < countCall; cnt++){
            inputCallData();
        }
    }

    // 호명된 번호의 값을 1로 변경
    private void inputCallData() throws NumberFormatException {
//        System.out.print("Call : ");
        String[] input = scan.nextLine().split(" 0")[0].split(" ");

        int inputCnt = input.length;
        int inputToInt = 0;

        // 호명된 number의 값을 1로 변경
        for(int cnt=0; cnt < inputCnt; cnt++) {
            inputToInt = Integer.parseInt(input[cnt]);
            if(inputToInt > 0 && inputToInt <= numberOfHuman) {
                student[inputToInt] = 1;
            }
        }
    }
}

// 최종 처리
class ProcessCheck {
    private int nonCallCnt = 0;
    private int numberOfHuman;
    private int[] student;

    // 호명이 안 된('X') 학생 수 count
    void checkXO(int numberOfHuman, int[] student) {
        this.numberOfHuman = numberOfHuman;
        this.student = student.clone();

        for (int cnt = 1; cnt <= numberOfHuman; cnt++) {
            nonCallCnt += student[cnt];
        }

        nonCallCnt = numberOfHuman - nonCallCnt;

        // Result
        System.out.println(nonCallCnt);
    }

}