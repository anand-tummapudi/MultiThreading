package org.anand.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();


        for(int i=0;i<10000;i++){
            service.execute(new Worker(i));
        }
        service.shutdown();;
    }
}

class Worker implements Runnable{
    private final int taskId;

    Worker(int taskId){
        this.taskId = taskId;
    }
    @Override
    public void run(){
        System.out.println("Task Id being Executed: " + this.taskId + " by Thread: " + Thread.currentThread().getName());
    }
}
