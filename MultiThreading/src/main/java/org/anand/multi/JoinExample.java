package org.anand.multi;

public class JoinExample {

    public static void main(String[] args) {

        // Create Thread with lambda
        System.out.println("Starting Threads");

        Thread t1 = new Thread(()->{
            for(int i=1;i<=5;i++){
                System.out.println("Thread 1 - Count: " + i);

            }
        });

        Thread t2 = new Thread(()->{
            for(int i=1;i<=10;i++){
                System.out.println("Thread 2 - Count: " + i);
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join(); // Main thread waits for t1 to fi
            t2.join();
             }catch(InterruptedException e){
                e.printStackTrace();
            }



        System.out.println("Completed Executing Threads");
    }
}
