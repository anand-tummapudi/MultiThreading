package org.anand.multi;

public class SynchronizationLocks {
    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increment1();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                increment2();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter1: " + counter1);
        System.out.println("Counter2: " + counter2);
    }

    private static  void increment1() {
        synchronized (object1){
        counter1++;
        }
     }
    private static  void increment2() {
        synchronized (object2) {
            counter2++;
        }
    }
}
