package org.anand.multi;

public class SynchronizationImpl {
    private static  int counter = 0;
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
             for(int i=0;i<100000;i++){
                 incrementCounter();
             }
         });

        Thread t2 = new Thread(()->{
            for(int i=0;i<100000;i++){
                incrementCounter();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Final Counter Value: " + counter);
    }

    private static synchronized void incrementCounter(){
        counter++;
    }
}
