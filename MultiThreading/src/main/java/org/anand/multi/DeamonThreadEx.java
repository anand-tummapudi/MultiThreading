package org.anand.multi;

public class DeamonThreadEx {
    public static void main(String[] args) {

        Thread deamon = new Thread (new DeamonThread());
        Thread userThread = new Thread(new UserThread());;
        deamon.setDaemon(  true); // Set as Deamon thread

        deamon.start();
        userThread.start();

        try {
            //deamon.join();
            userThread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Main Thread Completed");

    }
}

class DeamonThread implements Runnable{

    @Override
    public void run() {
        for(int i=1;i<=50;i++){
            System.out.println("Deamon Thread - Count: " + i);
            try{
                Thread.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

class UserThread implements Runnable {

    @Override
    public void run(){
        for(int i=0;i<10;i++){
            System.out.println("User Thread - Count: " + i);
        }
    }
}
