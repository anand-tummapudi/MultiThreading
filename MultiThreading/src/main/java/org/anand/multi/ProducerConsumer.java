package org.anand.multi;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer  {
    public static void main(String[] args) {
        List<Integer> container = new ArrayList<>();
        Worker worker = new Worker (0,5000,container);

        Thread t1 = new Thread(()->{
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(()->{
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}

class Worker {
    private final Object lock = new Object();
    private final List<Integer> container ;
    private final int min;
    private final int max;
    Worker(int min, int max, List <Integer> container){
        this.container = container;
        this.min = min;
        this.max = max;
    }
    public void produce() throws InterruptedException {
        synchronized(lock){

            while(true){

                if(container.size()== max){
                    try {
                        lock.wait();
                        System.out.println("Waiting for consume");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    container.add(1);
                    System.out.println("Produced: "+ container.size());
                    lock.notify();
                    Thread.sleep(500);
                }

            }
        }

    }

    public void consume() throws InterruptedException {

        synchronized(lock){
            while(true){
                if(container.size()==min){
                    try{
                        lock.wait();
                        System.out.println("Waiting for producer");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }else{
                    container.remove(container.size()-1);
                    System.out.println("Consumed: "+ container.size());
                    lock.notify();
                    Thread.sleep(500);
                }
            }
        }
    }
}
