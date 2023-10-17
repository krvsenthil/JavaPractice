package core;

import java.util.concurrent.TimeUnit;

public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        Message message = new Message("Process it");
        Reader reader = new Reader(message);
        new Thread(reader, "READER 1").start();
        Reader reader1 = new Reader(message);
        new Thread(reader1, "READER 2").start();
        Notifier notifier = new Notifier(message);
        new Thread(notifier, "NOTIFIER").start();
        System.out.println("All threads started");
    }
}

class Reader implements Runnable{
    private Message message;

    Reader(Message message){
        this.message = message;
    }

    @Override
    public void run(){
        String name = Thread.currentThread().getName();
        synchronized (message) {
            try {
                System.out.println(name+" before wait-->");
                message.wait();
                System.out.println(name+" waiting to get notified-->");
                System.out.println(name+" updated message value is -->"+message.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Notifier implements Runnable{
    private Message message;
    Notifier(Message message){
        this.message = message;
    }

    @Override
    public void run(){
        System.out.println("Inside core.Notifier--->");
        String name = Thread.currentThread().getName();
            try {
                TimeUnit.SECONDS.sleep(2);
                synchronized (message) {
                    message.setMessage(name+" - core.Notifier work done.");
                    //message.notify();
                    message.notifyAll();
                    System.out.println("core.Message is updated.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

class Message {
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}