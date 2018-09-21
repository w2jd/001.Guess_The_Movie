package com.company;

/*
- Question
        A 왕궁에서는 일렬로 길게 배치된 좌석이 있는데, 왕이 어딘가 원하는 자리에 앉으면 서열에 따라 좌우로 자리를 차례대로 앉아야 한다.
        왕이 7번 좌석에 앉으면 서열 1번은 6번, 서열 2번은 8번, 서열 3번은 5번 식으로 차례대로 자리가 배정된다.
        한 쪽이 끝나면 남은 쪽을 차례대로 채운다.
        안내를 담당한 시종을 위해 각 번호 별로 앉아야 할 자리를 정해주는 프로그램을 작성해 보자.
        좌석에 K명이 다 앉을 수 없는 경우는 -1을 출력한다.

        입력:
        좌석수 N, 왕의좌석번호 S, 인원수 K  (단 좌석번호는 1부터 시작하는 번호다)

        출력::
        1~N 까지의 좌석에 서열에 따라 앉아야 할 신하의 번호 (1~K)를 출력한다. 왕의 자리는 *로 표시하고 남은 자리는 0으로 표시한다.
        (*과 숫자는 빈칸으로 구분함)

        예시1:
        입력 : 6 2 4
        출력 : 1 * 2 3 4 0
        설명 : 왕의 좌석이 2번이고 서열 1번은 왼쪽 1번에., 2번은 오른쪽 3번에 앉고, 왼쪽은 끝났으므로 3, 4번은 오른쪽으로 차례로 앉고 오른쪽 끝에 한 자리가 남는다.
*/

import java.util.Scanner;

public class _008_Set_Seat {
    public static void main() {
        InputProcess008 inputProcess008 = new InputProcess008();
        Process008 process008 = new Process008();

        inputProcess008.inputInitialData();
        process008.setSeat(inputProcess008.getInputData());
    }

}

// Process : Input by User
class InputProcess008 {
    private int totalSeat, totalHuman, kingSeat;
    private int[] inputData;
    private Scanner scan = new Scanner(System.in);

    public int[] getInputData() {
        return inputData;
    }

    void inputInitialData() {
//        System.out.print("Input (좌석 수 / 왕의 좌석번호 / 인원 수) : ");
        String[] inputString = scan.nextLine().split(" ");

        // Set Variable
        // 0: 좌석수, 1: 왕의좌석번호, 2: 인원수
        inputData = new int[3];
        inputData[0] = Integer.parseInt(inputString[0]);
        inputData[1] = Integer.parseInt(inputString[1]);
        inputData[2] = Integer.parseInt(inputString[2]);
    }
}

// Process : set seat, print seat table
class Process008 {
    private int totalSeat, totalHuman, kingSeat;
    private int human = 1;  // human number
    private String[] seat;  // seat array

    void setSeat(int[] inputIntArray) {
        int seatNum;
        int nextCnt = 1;
        boolean plusMinus = false;

        // 0: 좌석수, 1: 왕의좌석번호, 2: 인원수
        totalSeat = inputIntArray[0];
        kingSeat = inputIntArray[1];
        totalHuman = inputIntArray[2];

        // if human can't seat then print -1 and erturn
        if (totalSeat <= totalHuman) {
            System.out.println("-1");
            return;
        }

        // Set seat array, Set king seat
        seat = new String[totalSeat + 1];
        seat[kingSeat] = "*";

        // Set seat to human
        while (seat[1] == null || seat[totalSeat] == null) {
            if (!plusMinus) {   // Left Seat
                seatNum = kingSeat - nextCnt;

                if (seatNum >= 1) {
                    if (human <= totalHuman) {
                        seat[seatNum] = String.valueOf(human);
                    } else {  // human > totalHuman
                        seat[seatNum] = String.valueOf(0);
                    }
                    human++;
                }
                plusMinus = true;
            } else {    // Right Seat : plusMinus == true
                seatNum = kingSeat + nextCnt;

                if (seatNum <= totalSeat) {
                    if (human <= totalHuman) {
                        seat[seatNum] = String.valueOf(human);
                    } else {    // human > totalHuman
                        seat[seatNum] = String.valueOf(0);
                    }
                    human++;
                }
                plusMinus = false;
                nextCnt++;
            }
        }
        printSeat();
    }

    // print seat table
    private void printSeat() {
        for (int cnt = 1; cnt <= totalSeat; cnt++) {
            System.out.print(seat[cnt] + " ");
        }
    }
}