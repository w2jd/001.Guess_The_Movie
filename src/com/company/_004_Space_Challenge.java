package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class _004_Space_Challenge {
    public static void main() throws FileNotFoundException {
        Simulation simul = new Simulation();
        ArrayList<Item> totalItem = new ArrayList();

        // Load item for File(phase-1.txt, phase-2.txt)
        totalItem = simul.loadItems("data/004.phase-1.txt");
        totalItem.addAll(simul.loadItems("data/004.phase-2.txt"));

        simul.loadU1(totalItem);

    }
}


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

    public boolean launch() {
        return true;
    }
    public boolean land() {
        return true;
    }

    public boolean canCarry(Item item) {
        carry(item);

        if (spaceWeight < currentWeight) {
            return false;
        } else {
            return true;
        }
    }
    public void carry(Item item) {
        resourceList.add(item.resourceName);
        currentWeight += item.resourceWeight;

        System.out.println("Carry : " + currentWeight + " / " + spaceWeight);
    }

    public boolean calcurateProbability() {
        return (int)(Math.random() * 2) == 1? true : false;
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
        spaceWeight = 10000;
        currentWeight = 0;
    }

    boolean isOK;
    public boolean launch() {
        isOK =  calcurateProbability();
        return isOK? false : true;
    }
    public boolean land() {
        isOK =  calcurateProbability();
        return isOK? false : true;
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
        spaceWeight = 29000;
        currentWeight = 0;
    }

    boolean isOK;
    public boolean launch() {
        isOK =  calcurateProbability();
        return isOK? false : true;
    }
    public boolean land() {
        isOK =  calcurateProbability();
        return isOK? false : true;
    }
}

class Simulation {
    private File scanFile;
    private Scanner scanByFile;


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

    void loadU1 (ArrayList<Item> items) {
        RocketU1 r_U1 = new RocketU1();

        for (int cnt=0; cnt < items.size(); cnt++) {
            if ( !r_U1.canCarry(items.get(cnt)) ) {
                System.out.println("break");
                break;
            }
        }
    }

}