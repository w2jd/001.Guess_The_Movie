/*
Date : 2018.08.27
Maker : LT
Description : Guess The Movie Title
 */

package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class _001_Guess_The_Movie_Title {
    public static void main() throws FileNotFoundException {
        FileControl filecontrol = new FileControl();
        CheckValue checkvalue = new CheckValue();

        // Get Movie name & replace alphabet to '_'
        String movieName = filecontrol.getMovieName();
        String movieGuess = movieName.replaceAll("[A-Z,a-z,0-9]","_");

        // Print Result
        if (checkvalue.playGame(movieName, movieGuess)) {
            System.out.println("\nYou win!!!");
        } else {
            System.out.println("\nYou lose...");
        }

        System.out.println("Answer is '" + movieName + "'.");
    }

}

// Control File I/O
class FileControl {
    private String fileName = "data/001.movielist.txt";
    private String movieName = null;
    private File movieFile = new File(fileName);
    Scanner scanByFile;
    private int movieSelectNumber = 0;

    public String getMovieName () throws FileNotFoundException {
        // Calculate Line Count
        int fileLineCount = getLineCount();

        // Select random number
        movieSelectNumber = (int)(Math.random() * fileLineCount);

        // Get the movie Name
        scanByFile = new Scanner(movieFile);
        for (int cnt = 0; cnt < movieSelectNumber; cnt++) {
            movieName = scanByFile.nextLine();
        }

        return movieName;
    }

    // Calculate Line Count
    private int getLineCount() throws  FileNotFoundException {
        int lineCnt = 0;
        scanByFile = new Scanner(movieFile);
        while (scanByFile.hasNextLine()) {
            scanByFile.nextLine();
            lineCnt++;
        }

        return lineCnt;
    }
}

// Comparison two value
class CheckValue {
    private String movieGuess;
    private int wrongCnt;
    private String wrongChar = "";

    // Print to Screen
    private String showScreen() {
        Scanner scanByUser = new Scanner(System.in);

        System.out.println("You are guessing : " + movieGuess);
        System.out.println("You have guessed (" + wrongCnt + ") wrong letters: " + wrongChar);
        System.out.print("==> ");

        return scanByUser.nextLine().toLowerCase();
    }

    public boolean playGame(String movieName, String movieGuessIn) {
        movieGuess = movieGuessIn;

        String inputByUser = null;

        for (wrongCnt = 0; (wrongCnt < 10) && !(movieGuess.equals(movieName)); ) {
            inputByUser = showScreen();

            if(!inputByUser.matches("[a-z]")) {
                continue;
            }

            // Check character contain input value by user.
            if (movieName.contains(inputByUser)) {  // Exist input character
                int existPoint = 0;

                // if Exist then get how many exist and position.
                for (int cnt = 0; cnt < movieName.length(); cnt++) {
                    if (movieName.indexOf(inputByUser, cnt) != -1) {
                        existPoint = movieName.indexOf(inputByUser, cnt);
                        StringBuilder modifyGuess = new StringBuilder(movieGuess);
                        try {   // If Input value is error. then Go for statement
                            modifyGuess.setCharAt(existPoint, inputByUser.charAt(0));
                        } catch (StringIndexOutOfBoundsException e) {
                            continue;
                        }
                        movieGuess = modifyGuess.toString();
                        cnt = existPoint;
                    }
                }

            } else {    // Not Exist input character : wrongCnt + 1, Add wrongChar + character
                if (!wrongChar.contains(inputByUser)) {
                    wrongCnt++;
                    wrongChar = wrongChar.concat(inputByUser + " ");
                }
            }

        }

        return (wrongCnt == 10) ? false : true;
    }

}

