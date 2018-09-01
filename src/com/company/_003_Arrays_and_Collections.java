package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _003_Arrays_and_Collections {
    public static void main() {
        StudyArrayList sal = new StudyArrayList();
        StudyStack ss = new StudyStack();
        StudyQueue sq = new StudyQueue();

        sal.play();
        ss.play();
        sq.play();
    }
}

class StudyArrayList {
    ArrayList scores = new ArrayList();

    void play() {
        System.out.println("\n=== ArrayList");

        add();  // Add item

        // Show item by for statement1 - use count variable
        System.out.print("\tSize : " + scores.size() + " | ");
        int arraySize = scores.size();
        for(int cnt = 0; cnt < arraySize; cnt++) {
            System.out.print(scores.get(cnt) + "\t");
        }

        // remove item(1)
        scores.remove(1);
        System.out.println("\n\t--- Remove index 1");

        // Show item by for statement2 - no need count variable
        System.out.print("\tSize : " + scores.size() + " | ");
        for (Object item : scores) {
            System.out.print(item + "\t");
        }

        System.out.print("\n\t82's index : " + scores.indexOf(82));
    }

    private void add() {
        scores.add(100);
        scores.add(99);
        scores.add(89);
        scores.add(97);
        scores.add(92);
        scores.add(88);
        scores.add(82);
    }
}

// Stack : Last in First Out
class StudyStack {
    Stack newsFeed = new Stack();

    void play() {
        System.out.println("\n=== Stack");

        add();  // Add item

        // Show item by for statement1 - use count variable
        System.out.print("\tSize : " + newsFeed.size() + " | ");
        int arraySize = newsFeed.size();
        for(int cnt = 0; cnt < arraySize; cnt++) {
            System.out.print(newsFeed.get(cnt) + "\t");
        }

        // pop item
        Object popItem = newsFeed.pop();
        System.out.println("\n\t--- pop : " + popItem);

        // pop item
        Object peekItem = newsFeed.peek();
        System.out.println("\t--- peek : " + peekItem);

        // Show item by for statement2 - no need count variable
        System.out.print("\tSize : " + newsFeed.size() + " | ");
        for (Object item : newsFeed) {
            System.out.print(item + "\t");
        }
    }

    private void add() {
        newsFeed.push("Morning_news");
        newsFeed.push("Afternoon_news");
        newsFeed.push("Evening_news");
    }
}

// Queue : First in First Out
// Deque : First in First Out + add or remove elements from either end of a both(Front of End)
class StudyQueue {
    Queue<String> ticketLine = new LinkedList();

    void play() {
        System.out.println("\n=== Queue");

        add();  // Add item

        // Show item by for statement1 - use count variable
        System.out.print("\tSize : " + ticketLine.size() + " | ");
        int arraySize = ticketLine.size();
        for(int cnt = 0; cnt < arraySize; cnt++) {
            System.out.print(ticketLine.poll() + "\t");
        }

    }

    private void add() {
        ticketLine.add("Hong");
        ticketLine.add("Cheon");
        ticketLine.add("Lee");
        ticketLine.add("Kim");
    }
}