package com.company;

import java.util.Scanner;

public class _005_Move_Zero {
    public static void main() {
        Scanner inputToUser = new Scanner(System.in);
        Solution sol = new Solution();
        String input = null;

        System.out.print("Input        : ");
        input = inputToUser.nextLine();

        System.out.println("Move to Zero : " + sol.moveZero(input));
    }
}

class Solution {
    public String moveZero(String inputNumber) {
        int count0 = 0;

        // Count 0. how many have 0
        for (int cnt=0; cnt< inputNumber.length(); cnt++) {
            if(inputNumber.charAt(cnt) == '0') {
                count0++;
            }
        }

        inputNumber = inputNumber.replaceAll("0","");

        for (int cnt=0; cnt<count0; cnt++) {
            inputNumber += "0";
        }

        return inputNumber;
    }
}