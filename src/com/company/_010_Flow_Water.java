package com.company;

/*
- Question
        어느 산의 등고행렬이 아래와 같이 주어진다. 각 숫자는 그 위치의 고도를 나타낸다.

        99 84 75 74 61 67
        91 91 98 84 73 65
        86 82 68 82 76 75
        77 70 73 80 66 80
        68 81 84 84 83 73
        65 54 44 44 48 47

        이런 등고행렬이 주어져 있을 때 물이 흘러서 도착할 수 있는 곳을 모두 찾으려고 한다.
        (0, 0) 번이 정상이고 물줄기가 출발하는 곳이다. 물은 고도가 같거나 낮은 곳으로 흘러간다. 주어진 행렬에 대해 물이 도착할 수 있는 곳을 모두 1로 표시하고 도착할 수 없는 곳을 0으로 표시한 행렬을 출력하시오.

        입력:
        행렬의 크기 N이 첫 줄에 나오고
        N x N의 숫자 행렬이 주어진다. 각 숫자는 [0, 100) 구간의 값이다.

        출력:
        주어진 행렬에 대해 물이 도착할 수 있는 곳을 모두 1로, 도착할 수 없는 곳을 0으로 표시한 행렬을 출력한다. (N x N개 숫자가 차례대로 나와야 함)
        3번 예시가 틀려서 수정했습니다. 고도가 같으면 물이 흘러갈 수 있음 (테스트 케이스 오류 수정 9/20 7am 만들기는 어려웠는데 풀이는 단순하네요...)
*/

import java.util.Scanner;

public class _010_Flow_Water {
    public static void main() {
        InputProcess010 inputProcess010 = new InputProcess010();
        Process010 process010 = new Process010();

        process010.initProcess(inputProcess010.getInput(), inputProcess010.getRowAndcol());
        process010.flowWater();
    }
}

class InputProcess010 {
    private int rowAndcol;
    private int[][] input;  // input By User
    private Scanner scan = new Scanner(System.in);

    public int getRowAndcol() {
        return rowAndcol;
    }

    public int[][] getInput() {
        return input;
    }

    InputProcess010() {
        inputRowAndCol();
        inputArray();
    }

    // Function : Input Row & Col
    private void inputRowAndCol() {
        rowAndcol = scan.nextInt();

        input = new int[rowAndcol][rowAndcol];
    }

    // Function : Input Array
    private void inputArray() {
        for (int rowcnt = 0; rowcnt < rowAndcol; rowcnt++) {
            for (int colcnt = 0; colcnt < rowAndcol; colcnt++) {
                input[rowcnt][colcnt] = scan.nextInt();
            }
        }
    }
}

class Process010 {
    private int[][] valley;     // valley table
    private int[][] inputArray; // input By User
    private int RowandCol;

    void initProcess(int[][] inputArray, int inputRowandCol) {
        this.inputArray = inputArray;
        RowandCol = inputRowandCol;

        valley = new int[RowandCol][RowandCol];
    }

    // Function : Calculate valley table
    void flowWater() {
        // ↘ : (0,0) → (RowandCol, RowandCol)
        for (int rowcnt = 0; rowcnt < RowandCol; rowcnt++) {
            for (int colcnt = 0; colcnt < RowandCol; colcnt++) {
                if (rowcnt == 0 && colcnt == 0) {
                    valley[0][0] = 1;
                } else {
                    if ((colcnt != 0) && (inputArray[rowcnt][colcnt - 1] >= inputArray[rowcnt][colcnt]) && (valley[rowcnt][colcnt - 1] == 1)) {    // →
                        valley[rowcnt][colcnt] = 1;
                    }
                    if ((rowcnt != 0) && (inputArray[rowcnt - 1][colcnt] >= inputArray[rowcnt][colcnt]) && (valley[rowcnt - 1][colcnt] == 1)) {    // ↓
                        valley[rowcnt][colcnt] = 1;
                    }
                }
            }
        }

        // ↖ : (RowandCol, RowandCol) → (0,0)
        for (int rowcnt = RowandCol - 1; rowcnt >= 0; rowcnt--) {
            for (int colcnt = RowandCol - 1; colcnt >= 0; colcnt--) {
                if ((colcnt != 0) && (inputArray[rowcnt][colcnt - 1] <= inputArray[rowcnt][colcnt]) && (valley[rowcnt][colcnt] == 1)) {    // ←
                    valley[rowcnt][colcnt - 1] = 1;
                }
                if ((rowcnt != 0) && (inputArray[rowcnt-1][colcnt] <= inputArray[rowcnt][colcnt]) && (valley[rowcnt][colcnt] == 1)) {    // ↑
                    valley[rowcnt - 1][colcnt] = 1;
                }
            }
        }

        printValley();
    }

    // Function : Print Valley table
    void printValley() {
        for (int rowcnt = 0; rowcnt < RowandCol; rowcnt++) {
            for (int colcnt = 0; colcnt < RowandCol; colcnt++) {
                System.out.print(valley[rowcnt][colcnt] + " ");
            }
            System.out.println();
        }
    }
}