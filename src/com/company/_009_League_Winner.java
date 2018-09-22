package com.company;

/*
- Question
        N개 팀 간의 리그전의 전적으로 우승팀을 찾는 프로그램을 작성한다.
        각 팀은 이긴경기*3, 동점경기*1 만큼의 승점을 얻는다.
        승점이 같은 경우 1) 이긴 게임의 수, 2) 득점수가 높은 순, 3) 실점수가 낮은 순으로 등수를 결정하는데, 그래도 동점인 경우는 공동우승으로 한다.
        - 입력
        팀 수 N  (팀 이름은 1~N이다.)
        경기 전적이 N*(~N-1)번 나온다. 각 경기전적은 팀1이름 팀2이름 팀1점수 팀2점수의 순으로 네 개의 숫자가 나온다.
        예를 들어 한 줄에 2 3 1 0이면 2번팀과 3번팀의 경기에서 2번팀이 1:0으로 이겼다는 뜻이 된다.

        - 출력
        우승한 팀의 이름(숫자) 한개, 또는
        우승팀이 위의 규칙에 의해 가려지지 않는 경우 승점과 위의 1), 2), 3)까지 동점인 팀의 이름을 오름차순으로 출력한다.

        - 예시1
        3팀의 전적이 들어왔고 1번팀은 1승1패, 2번팀은 2패, 3번팀은 2승으로 3번이 우승이다.
        - input
            6
            1 2 0 0
            1 3 0 3
            1 4 2 2
            1 5 1 2
            1 6 0 2
            2 3 3 3
            2 4 1 4
            2 5 2 1
            2 6 3 4
            3 4 2 0
            3 5 3 3
            3 6 3 4
            4 5 1 3
            4 6 0 0
            5 6 3 2
        - output
            5 6
*/

import java.util.Scanner;

public class _009_League_Winner {
    public static void main() {
        InputProcess009 inputProcess009 = new InputProcess009();
        Process009 process009 = new Process009();

        inputProcess009.inputTeamNumber();
        inputProcess009.inputPlayScore();
        process009.initialize(inputProcess009.getTeam(), inputProcess009.getTotalTeam(), inputProcess009.getteam_ItemCount());
        process009.findWinner();
    }

}

class InputProcess009 {
    private int totalTeam;
    private int totalPlay;
    private int[][] team;
    // team[N][team_WinPoint, team_GameWin, team_GoalPlustCount, team_GoalMinusCount]
    private int team_WinPoint = 0, team_GameWin = 1, team_GoalPlusCount = 2, team_GoalMinusCount = 3;
    private int team_ItemCount = 4;

    private Scanner scan = new Scanner(System.in);

    int getTotalTeam() {
        return totalTeam;
    }

    int[][] getTeam() {
        return team;
    }

    int getteam_ItemCount() {
        return team_ItemCount;
    }

    // Function : Input Team Number ; 총 팀 수를 입력받음.
    void inputTeamNumber() {
//        System.out.print("팀 수 : ");
        totalTeam = scan.nextInt();
    }

    // Function : n! = n + (n-1) + (n-2) + ... + 1
    private int calCount(int input) {
        if (input == 1) {
            return 1;
        } else {
            return input + calCount(input - 1);
        }
    }

    // Function : Input Play Score
    // Input struct : teamX teamY scoreX scoreY
    void inputPlayScore() {
        int teamX, teamY, scoreX, scoreY;

        team = new int[totalTeam + 1][team_ItemCount + 1];
        totalPlay = calCount(totalTeam - 1);

        for (int cnt = 1; cnt <= totalPlay; cnt++) {
//            System.out.print("Input Score : ");

            teamX = scan.nextInt();
            teamY = scan.nextInt();
            scoreX = scan.nextInt();
            scoreY = scan.nextInt();

            if (scoreX > scoreY) {  // teamX Win
                team[teamX][team_WinPoint] += 3;
                team[teamX][team_GameWin] += 1;
                team[teamX][team_GoalPlusCount] += scoreX;
                team[teamX][team_GoalMinusCount] -= scoreY;

                team[teamY][team_GoalPlusCount] += scoreY;
                team[teamY][team_GoalMinusCount] -= scoreX;
            } else if (scoreX < scoreY) {   // teamY Win
                team[teamY][team_WinPoint] += 3;
                team[teamY][team_GameWin] += 1;
                team[teamY][team_GoalPlusCount] += scoreY;
                team[teamY][team_GoalMinusCount] -= scoreX;

                team[teamX][team_GoalPlusCount] += scoreX;
                team[teamX][team_GoalMinusCount] -= scoreY;
            } else if (scoreX == scoreY) {    // tie score
                team[teamX][team_WinPoint] += 1;
                team[teamX][team_GoalPlusCount] += scoreX;
                team[teamX][team_GoalMinusCount] -= scoreY;

                team[teamY][team_WinPoint] += 1;
                team[teamY][team_GoalPlusCount] += scoreY;
                team[teamY][team_GoalMinusCount] -= scoreX;
            }
        }
    }
}

class Process009 {
    private int[][] team;
    private int totalTeam, totalItem;
    private int winner[];
    private int winnerCount = 1;

    // InputProcess009 로부터 값을 받아서 초기화
    void initialize(int[][] totalteam, int totalTeam, int totalItem) {
        this.team = totalteam.clone();
        this.totalTeam = totalTeam;
        this.totalItem = totalItem;

        winner = new int[totalTeam + 1];
    }

    // Function : item 값을 비교하며 winner 찾기
    void findWinner() {
        int tempWinner;

        for (int cnt = 1; cnt <= totalTeam; cnt++) {
            tempWinner = compareTeam(winner[winnerCount], cnt, 0);

            if (tempWinner == 0 && cnt != 1) {
                winner[++winnerCount] = cnt;
            } else {
                winnerCount = 1;
                winner[winnerCount] = tempWinner;
            }
        }
        printWinner();
    }

    // Function : Team 1 vs Team 2
    // Return : Winner Team number
    //          if same score then return 0
    private int compareTeam(int teamA, int teamB, int item) {
        if (item == totalItem) {
            return 0;
        }

        if (team[teamA][item] > team[teamB][item]) {
            return teamA;
        } else if (team[teamA][item] < team[teamB][item]) {
            return teamB;
        } else {    // team[teamA][item] == team[teamB][item]
            return compareTeam(teamA, teamB, ++item);
        }
    }

    // Print Winner to Screen
    private void printWinner() {
        for (int cnt = 1; cnt <= winnerCount; cnt++) {
            if (cnt == 1) {
                System.out.print(winner[cnt]);
            } else {
                System.out.print(" " + winner[cnt]);
            }
        }
    }
}