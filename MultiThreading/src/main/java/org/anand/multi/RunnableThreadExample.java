package org.anand.multi;

import java.util.stream.IntStream;

public class RunnableThreadExample {

    public static void main(String[] args) {

        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread (new ThreadTwo());

        one.start();

        two.start();

//        IntStream.range(1,10).forEach(i -> {
//            System.out.println("Main Thread: "+i);
//        });

        Thread th = new Thread (new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<20;i++){
                    System.out.println("Anonymous Thread: "+i);
                }
            }
        }) ;

        th.start();

        Thread lambdaThread = new Thread(()->{
            IntStream.range(1,20).forEach(i->{
                System.out.println("Lambda Thread: "+i);
            });
        });
        lambdaThread.start();
    }
}

class ThreadOne implements Runnable{
    @Override
    public void run(){
        for(int i=0;i<20;i++){
            System.out.println("ThreadOne: "+i);
        }
    }
}

class ThreadTwo implements Runnable{
    @Override
    public void run(){
//        IntStream.range(1,10).forEach(i -> {
//            System.out.println("ThreadTwo: "+i);
//        });
        for(int i=0;i<20;i++){
            System.out.println("ThreadTwo: "+i);
        }
    }
}
