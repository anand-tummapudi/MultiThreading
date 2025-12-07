package org.anand.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadpoolDemo {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        for(int i=1;i<=10;i++){
            service.execute(new Work(i));
        }
        service.shutdown();
    }
}

class Work implements Runnable{

    private final int workid;

    public Work(int workid){
        this.workid = workid;
    }
     @Override
    public void run(){
        System.out.println("Work Id being Executed :"+this.workid +"by Thread: "+ Thread.currentThread().getName());
      try
      {
          Thread.sleep(500);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
}
