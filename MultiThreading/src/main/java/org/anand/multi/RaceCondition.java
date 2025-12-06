package org.anand.multi;

public class RaceCondition implements Runnable {

    int sharedCounter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            sharedCounter++;
        }
    }

    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return sharedCounter;
    }

    public static void main(String[] args) throws InterruptedException {
        RaceCondition thread1 = new RaceCondition();
        Thread t1 = new Thread(thread1);
        Thread t2 = new Thread(thread1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final Counter Value: " + thread1.getCounter());
    }
}