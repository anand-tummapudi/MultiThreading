package org.anand.multi;

public class ThreadsPriorityExample {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getPriority()); // Main thread priority
        System.out.println(Thread.currentThread().getName());

        //Thread.currentThread().setPriority(Thread.MAX_PRIORITY) ; // Set main thread priority to max

        System.out.println(Thread.currentThread().getPriority()); // Main thread priority after change
        System.out.println(Thread.currentThread().getName());

        Thread t1 = new Thread(()->{
            System.out.println(("Thread 1 Priority: " + Thread.currentThread().getPriority()));
        });

        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();

        System.out.println("Main Thread Executing ...");

    }
}
