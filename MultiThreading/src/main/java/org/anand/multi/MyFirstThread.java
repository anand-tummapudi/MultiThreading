package org.anand.multi;

public class MyFirstThread extends Thread{

    @Override
    public void run(){
            for(int i=0;i<10;i++){
                System.out.println("MyFirstThread: "+i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }

    public static void main (String args[]){
        MyFirstThread t1 = new MyFirstThread();
        t1.start();

    }

}
