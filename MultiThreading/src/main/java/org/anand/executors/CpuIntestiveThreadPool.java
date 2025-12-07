package org.anand.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntestiveThreadPool {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Cores : "+cores);
        ExecutorService service = Executors.newFixedThreadPool(cores);
        for(int i=0;i<30;i++){
           service.execute(new CpuTask(i));
        }
    }
}

class CpuTask implements Runnable{
  private final int taskid;

    public CpuTask(int taskid) {
            this.taskid = taskid;
        }
    @Override
    public void run() {
        System.out.println("Task -- "+this.taskid +"  -> CPU Intesive Task Started by "+Thread.currentThread().getName());
    }
}
