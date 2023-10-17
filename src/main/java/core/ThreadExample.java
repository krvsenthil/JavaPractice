package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ThreadExample extends Thread {

    private String externalValue;

    ThreadExample(String externalValue){
       this.externalValue = externalValue;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadExample te = new ThreadExample("Hello World!");
        te.start();
        te.join();
        Thread te1 = new Thread(ThreadExample::execute);
        te1.start();
    }

    public static void execute(){
        System.out.println("Thread Name static method :-->"+Thread.currentThread().getName());
    }

    @Override
    public void run(){
        System.out.println("Thread Variable State:-->"+this.externalValue);
        System.out.println("Inside Thread run method:-->"+Thread.currentThread().getName());
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run(){
        try {
            System.out.println("Run about to work");
            work();
            System.out.println("Back from work");
        }catch (InterruptedException e){
            System.out.println("Work is interrupted");
            return;
        }
    }

    public void work() throws InterruptedException{
        while(true){
            System.out.println("Inside work-->"+Thread.currentThread().getName());
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("isInterupted--->"+Thread.currentThread().isInterrupted());
                /* it clears the interrupted flag */
                //System.out.println(Thread.interrupted());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Will not execute");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            MyRunnable myRunnable = new MyRunnable();
            Thread thread = new Thread(myRunnable);
            thread.start();

            TimeUnit.SECONDS.sleep(2);

            System.out.println("Interrupting other thread ");

            thread.interrupt(); //interrupt this thread

            thread.join();//waits till to terminate the current thread

            Thread.yield();

            System.out.println("<----Main Leaving--->");

        }catch (InterruptedException interruptedException){
            System.out.println("Sleep exception :-->"+interruptedException.getMessage());
        }
    }
}

class MyRunnableRuntimeException {
    public static void main(String[] args) {
        //to show how to throw checked exception from run method
        Thread t = new Thread(()->{
            try {
                Files.readString( Paths.get("testing-file"));
                System.out.println("Thread Name :-->" + Thread.currentThread().getName());
            }catch (IOException ioException){
                throw new RuntimeException(ioException);
            }
        });
        t.start();
        System.out.println("Main Thread Name :-->"+Thread.currentThread().getName());
    }
}