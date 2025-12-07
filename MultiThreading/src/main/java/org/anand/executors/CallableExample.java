package org.anand.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        ;
        Future<Integer> result = service.submit(new ReturnValueTask());
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

  class ReturnValueTask implements Callable<Integer> {

    public Integer call() throws Exception {
        System.out.println("ReturnValueTask Started by "+Thread.currentThread().getName());
        return 12;
    }

 }
