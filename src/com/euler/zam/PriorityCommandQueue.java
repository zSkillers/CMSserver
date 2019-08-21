package com.euler.zam;

import java.util.PriorityQueue;

public class PriorityCommandQueue implements Runnable {

    public static PriorityQueue<String> priorityQueue = new PriorityQueue();

    @Override
    public void run() {
        String s;
        System.out.println("Main Runnable Thread Start.");
        while (Main.serverIsOn) {
            if (PriorityCommandQueue.priorityQueue.size() > 0) {
                    s = PriorityCommandQueue.priorityQueue.poll();
                    //Main.logs.addLog("Tread Output: " + s + ":" + PriorityCommandQueue.priorityQueue.size());
                    System.out.println("Tread Output: " + s + ":" + PriorityCommandQueue.priorityQueue.size());
                }
            }
            //System.out.println("here");

        System.out.println("Size: " + PriorityCommandQueue.priorityQueue.size());
        System.out.println("Main Runnable Thread Exited.");
    }
}
