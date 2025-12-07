package org.anand.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 10; i++) {
                CpuTask t = new CpuTask(i);
                executor.execute(t);
            }
        executor.shutdown();
        }



}

class Task implements Runnable{

    private final int taskid;

    public Task(int id){
        this.taskid = id;
    }

   @Override
    public void run(){
        System.out.println("Task Id being Executed: " + this.taskid + " by Thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
