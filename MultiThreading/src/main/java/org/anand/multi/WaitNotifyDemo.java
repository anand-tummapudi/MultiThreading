package org.anand.multi;

public class WaitNotifyDemo {
    private static final Object LOCK = new Object();
    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            t1Work();
        });

        Thread t2 = new Thread(()->{
            t2Work();
        });

        t1.start();
        t2.start();
    }

    private static void t1Work() {
        synchronized (LOCK) {
            System.out.println("T1: Holding lock...");
            try {
                System.out.println("T1: Waiting for notification...");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T1: Notified and resumed.");
        }
    }

    private static void t2Work() {
        synchronized (LOCK) {
            System.out.println("T2: Holding lock...");
            System.out.println("T2: Notifying T1...");
            LOCK.notify();
            System.out.println("T2: Notification sent.");
        }
    }
}
