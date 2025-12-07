package org.anand.multi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationWithLock {

    private final Lock lock = new ReentrantLock();
    private int count =0;
    public static void main(String[] args) {
        SynchronizationWithLock syncCounter = new SynchronizationWithLock();
        Thread t1 = new Thread(()->{
            for(int i=0;i<1000;i++){
                syncCounter.increment();
            }
        });

        Thread t2 = new Thread(()->{
            for(int i=0;i<1000;i++){
                syncCounter.increment();
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

        System.out.println("Final Count: " + syncCounter.getCount());

    }

    private void increment(){
        lock.lock();
        try {
            count++;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        }

        private int getCount() {
            lock.lock();
            try {
                return count;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return -1;
        }

}
