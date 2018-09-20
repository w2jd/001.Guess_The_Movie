package com.company;

import java.util.Scanner;

/*
- Question
    동주는 프로그래밍 콘테스트에 참가했다. 이 대회는 각 문제에 대해 모든 테스트 케이스를 맞추면 10점, 틀린 것이 있을 경우 맞춘 테스트케이스 수를 점수로 받게 된다. 참가지 수 N과 문제 수 K에 대해 참가자 별 맞힌 테스트케이스 개수가 주어졌을 때 일등인 사람의 번호를 출력한다.
    동점이 여러 명 있을 경우 1) 만점이 많은 순, 2) 테스트케이스 맞춘 수가 많은 순, 3) 번호 순서(먼저 나온 것)로 일등을 선발한다.

    입력:
    첫 줄에 참가자 수 N과 문제수 K가 주어진다.
    두번째 줄에 K개 문제의 테스트케이스 수가 주어진다.
    다음 줄부터 N명의 참가자에 대해 K개 문제별로 맞춘 테스트케이스 수가 K개의 숫자로 주어진다.

    출력:
    위의 기준에 의해 일등인 사람의 번호가 출력된다. 번호는 입력된 순서로 1번부터 번호가 매겨진다.

    예시1:
    3명이고 문제수는 2이다. 두 문제의 테스트 케이스는 둘 다 5개씩이다. (두번째 줄에 입력된 숫자 2개)
    1번 선수(3 5)는 13점, 1번 선수(4 4)는 8점, 3번 선수(5 4)는 14점으로 3번이 1등이다.
    답은 3

    - Input
    3 2
    5 5
    3 5
    4 4
    5 4
    - Output / Result
    3

*/

public class _007_Calculate_The_Rank {
    public static void main() {
        InputProcess inputProcess = new InputProcess();
        Process007 process007 = new Process007();

        inputProcess.inputInitialData();
        inputProcess.callToinputUserData();
        process007.find1st(inputProcess.getUser());

    }
}

class User007 {
    static int user_item2 = 2; // numberOfAllCorrect, numberOfCorrect
    static int numberOfAllCorrect = 0;
    static int numberOfCorrect = 1;
}

class InputProcess {
    private int numberOfUser;
    private int numberOfQuestion;
    private int[] testCase;
    private int[][] user;

    Scanner scan = new Scanner(System.in);

    InputProcess() {
        numberOfUser = 0;
        numberOfQuestion = 0;
    }

    public int[][] getUser() {
        return user;
    }

    // 초기 입력 처리 : input1, input2
    void inputInitialData() {
        inputUserAndQuestion();
        inputTestCase();
    }

    // input1 : 참가자 수, 문제 수
    void inputUserAndQuestion() {
//        System.out.print("Input (참가자 수 / 문제 수) : ");
        int[] input = convertStringToIntArray(scan.nextLine());

        numberOfUser = input[0];
        numberOfQuestion = input[1];

        // Initial user
        user = new int[numberOfUser + 1][User007.user_item2];
    }

    // input2 : TestCase 수
    void inputTestCase() {
//        System.out.print("Input (TestCase) : ");

        testCase = convertStringToIntArray(scan.nextLine());
    }

    // user수 만큼 inputUserData 실행
    void callToinputUserData() {
        for (int userid = 1; userid <= numberOfUser; userid++) {
            inputUserData(userid);
        }
    }

    // 각 사용자의 값을 저장
    void inputUserData(int userid) {
//        System.out.print("Input [" + userid + "] : ");
        int[] input = convertStringToIntArray(scan.nextLine());

        for (int cnt = 0; cnt < numberOfQuestion; cnt++) {
            if (input[cnt] == testCase[cnt]) {
                user[userid][User007.numberOfAllCorrect] += 1;
            } else if (input[cnt] < testCase[cnt]) {
                user[userid][User007.numberOfCorrect] = input[cnt];
            }
        }
    }

    // 입력받은 String(' '로 구분된 숫자로 구성된 문자열)을 int형 Array로 변환
    int[] convertStringToIntArray(String inputContent) {
        String[] inputCharArray = inputContent.split(" ");
        int itemCount = inputCharArray.length;
        int[] outputIntArray = new int[itemCount];

        for (int cnt = 0; cnt < itemCount; cnt++) {
            outputIntArray[cnt] = Integer.parseInt(inputCharArray[cnt]);
        }

        return outputIntArray;
    }
}


class Process007 {
    private int[][] user;
    private int [] winner;

    private void setUser(int[][] inputUser) {
        this.user = inputUser.clone();
    }

    void find1st(int[][] inputUser) {
        setUser(inputUser);

        winner = new int[User007.user_item2 + 1];

        // Init winner
        winner[User007.numberOfAllCorrect] = user[1][User007.numberOfAllCorrect];
        winner[User007.numberOfCorrect] = user[1][User007.numberOfCorrect];
        winner[User007.user_item2] = 1;

        for(int cnt = 1; cnt < user.length; cnt++) {
            // Condition 1 : 만점이 많은 순
            if(winner[User007.numberOfAllCorrect] < user[cnt][User007.numberOfAllCorrect]) {
                winner[User007.numberOfAllCorrect] = user[cnt][User007.numberOfAllCorrect];
                winner[User007.numberOfCorrect] = user[cnt][User007.numberOfCorrect];
                winner[User007.user_item2] = cnt;
            } else if(winner[User007.numberOfAllCorrect] == user[cnt][User007.numberOfAllCorrect]) {
                // Condition 2 : testcase 맞춘 수가 많은 순
                if(winner[User007.numberOfCorrect] < user[cnt][User007.numberOfCorrect]) {
                    winner[User007.numberOfAllCorrect] = user[cnt][User007.numberOfAllCorrect];
                    winner[User007.numberOfCorrect] = user[cnt][User007.numberOfCorrect];
                    winner[User007.user_item2] = cnt;
                }
            }
        }

        // Result
        System.out.println(winner[User007.user_item2]);
    }
}