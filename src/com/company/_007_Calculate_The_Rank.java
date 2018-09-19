package com.company;

import java.util.Scanner;

/*
- Question

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