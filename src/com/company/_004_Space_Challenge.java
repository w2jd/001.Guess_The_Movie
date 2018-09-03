package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class _004_Space_Challenge {
    static String minCost_Rocket = null, maxCost_Rocket = null;
    static int minCost_Count = 0, maxCost_Count = 0;
    static int minCost = Integer.MAX_VALUE, maxCost = 0;
    static int simulCount = 0;
    static Simulation simul = simul = new Simulation();;
    static ArrayList<Item> totalItem = new ArrayList();

    public static void main() throws FileNotFoundException {

        Scanner inputByUser = new Scanner(System.in);

        System.out.println("=== Space Challenge ===");
        System.out.print("Input Simulation count : ");
        simulCount = inputByUser.nextInt();

        // Load item for File(phase-1.txt, phase-2.txt)
        totalItem = simul.loadItems("data/004.phase-1.txt");
        totalItem.addAll(simul.loadItems("data/004.phase-2.txt"));

        // Run Simulation
        // Result : Rocket count, cost
        runSimulation_Many();

        System.out.println("--- Result ---");
        System.out.println("min Cost Rocket : " + minCost_Rocket + " | count : " + minCost_Count + " | cost : " + minCost + " Million");
        System.out.println("Max Cost Rocket : " + maxCost_Rocket + " | count : " + maxCost_Count + " | cost : " + maxCost + " Million");
    }

    static void runSimulation_Many() {
        int tempMinCost = 0;

        for(int cnt = 0; cnt < simulCount; cnt++) {
            int[] result_U1 = simul.runSimulation(totalItem, "U1");
            int[] result_U2 = simul.runSimulation(totalItem, "U2");

            tempMinCost = Integer.min(result_U1[1], result_U2[1]);
            if (tempMinCost < minCost) {
                if (tempMinCost == result_U1[1]) {
                    minCost = tempMinCost;
                    minCost_Count = result_U1[0];
                    minCost_Rocket = "U1";
                    if (result_U2[1] > maxCost) {
                        maxCost = result_U2[1];
                        maxCost_Count = result_U2[0];
                        maxCost_Rocket = "U2";
                    }
                } else {
                    minCost = result_U2[1];
                    minCost_Count = result_U2[0];
                    minCost_Rocket = "U2";
                    if (result_U1[1] > maxCost) {
                        maxCost = result_U1[1];
                        maxCost_Count = result_U1[0];
                        maxCost_Rocket = "U1";
                    }
                }
            }
        }
    }
}

// Declare Resource(item) class
class Item {
    String resourceName;
    int resourceWeight;
}

interface SpaceShip {
    boolean launch();
    boolean land();

    boolean canCarry(Item item);
    void carry(Item item);
}

class Rocket implements SpaceShip {
    ArrayList<String> resourceList = new ArrayList();

    int spaceWeight = 0;
    int currentWeight = 0;
    int cost = 0;

    public boolean launch() {
        return true;
    }
    public boolean land() {
        return true;
    }

    // verify carry item
    public boolean canCarry(Item item) {
        carry(item);

        // if can't carry item than remove item to resourceList
        if (spaceWeight < currentWeight) {
            resourceList.remove(item.resourceName);
            currentWeight -= item.resourceWeight;
            return false;
        } else {    // success carry item
            return true;
        }
    }

    // add item to resourceList
    public void carry(Item item) {
        resourceList.add(item.resourceName);
        currentWeight += item.resourceWeight;
    }

    // calculate probability
    public double calculateProbability() {
        return ((double)currentWeight / spaceWeight) * Math.random();
    }
}

class RocketU1 extends Rocket {
    /* Requirement
    Rocket cost = $100 Million
    Rocket weight = 10 Tonnes
    Max weight (with cargo) = 18 Tonnes
    Chance of launch explosion = 5% * (cargo carried / cargo limit)
    Chance of landing crash = 1% * (cargo carried / cargo limit)
    */
    RocketU1() {
        spaceWeight = 8000; // Rocket Max weight - Rocket weight, it is only cargo weight.
        currentWeight = 0;
        cost = 100;
    }

    public boolean launch() {
        if (calculateProbability() < 0.05) {    // reflect chance 0.05 on requirement
            return false;
        } else {
            return true;
        }
    }
    public boolean land() {
        if (calculateProbability() < 0.01) {    // reflect chance 0.01 on requirement
            return false;
        } else {
            return true;
        }
    }
}

class RocketU2 extends Rocket {
    /* Requirement
    Rocket cost = $120 Million
    Rocket weight = 18 Tonnes
    Max weight (with cargo) = 29 Tonnes
    Chance of launch explosion = 4% * (cargo carried / cargo limit)
    Chance of landing crash = 8% * (cargo carried / cargo limit)
    */
    RocketU2() {
        spaceWeight = 11000; // Rocket Max weight - Rocket weight, it is only cargo weight.
        currentWeight = 0;
        cost = 120;
    }

    public boolean launch() {
        if (calculateProbability() < 0.04) {// reflect chance 0.04 on requirement
            return false;
        } else {
            return true;
        }
    }
    public boolean land() {
        if (calculateProbability() < 0.08) {// reflect chance 0.08 on requirement
            return false;
        } else {
            return true;
        }
    }
}

class Simulation {
    private File scanFile;
    private Scanner scanByFile;

    RocketU1 r_U1;
    RocketU2 r_U2;

    // Import item to File
    ArrayList loadItems(String filePath) throws FileNotFoundException {
        ArrayList<Item> items = new ArrayList();
        Item tempitem;
        String tempLine;
        int splitPoint;

        scanFile = new File(filePath);
        scanByFile = new Scanner(scanFile);

        while (scanByFile.hasNextLine()) {
            tempLine = scanByFile.nextLine();
            tempitem = new Item();
            splitPoint = (tempLine.indexOf("="));

            tempitem.resourceName = tempLine.substring(0, splitPoint);
            tempitem.resourceWeight = Integer.parseInt(tempLine.substring(splitPoint+1));
            items.add(tempitem);
        }

        return items;
    }

    ArrayList loadU1 (ArrayList<Item> items) {
        r_U1 = new RocketU1();

        while (items.size() != 0) {
            if ( !r_U1.canCarry(items.get(0)) ) {
                break;
            } else {
                items.remove(0);
            }
        }

        return r_U1.resourceList;
    }

    ArrayList loadU2 (ArrayList<Item> items) {
        r_U2 = new RocketU2();

        while (items.size() != 0) {
            if ( !r_U2.canCarry(items.get(0)) ) {
                break;
            } else {
                items.remove(0);
            }
        }

        return r_U2.resourceList;
    }

    // Calculate total cost
    int[] runSimulation (ArrayList<Item> inputTotalItems, String rocketType) {
        int totalCost = 0;
        int totalCount = 0;
        ArrayList<Item> items = new ArrayList();
        items.addAll(inputTotalItems);

        if (rocketType == "U1") {
            while (!items.isEmpty()) {
                loadU1(items);

                do {
                    totalCost += r_U1.cost;
                    totalCount++;
                } while (!(r_U1.launch() && r_U1.land()));
            }
        } else if (rocketType == "U2") {
            while (!items.isEmpty()) {
                loadU2(items);

                do {
                    totalCost += r_U2.cost;
                    totalCount++;
                } while (!(r_U2.launch() && r_U2.land()));
            }
        }

        return new int[]{totalCount, totalCost};
    }
}