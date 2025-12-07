package org.anand.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new ProbeTask(),0,1, TimeUnit.SECONDS);

        try {
            if (!service.awaitTermination(30, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            e.printStackTrace();
        }
    }
}

class ProbeTask implements Runnable{
     @Override
    public void run(){
        System.out.println("Probe Task executed by Thread: " + Thread.currentThread().getName());
    }

}
