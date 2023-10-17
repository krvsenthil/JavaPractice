package core;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors
                .newScheduledThreadPool(4);
        scheduledExecutorService.schedule(()->{
            System.out.println("Schedule Example:-->"+Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        System.out.println("---------------");

        ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(4);
        scheduledExecutorService1.scheduleAtFixedRate(()->{
            System.out.println("<---:Schedule at fixed rate:--->");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);
        scheduledExecutorService1.awaitTermination(7,TimeUnit.SECONDS);
        scheduledExecutorService1.shutdown();
        System.out.println("---------------");

        ScheduledExecutorService scheduledExecutorService2 = Executors.newScheduledThreadPool(4);
        scheduledExecutorService2.scheduleWithFixedDelay(()->{
            System.out.println("<---:Schedule at fixed delay:--->");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        },500,1000, TimeUnit.MILLISECONDS);
        scheduledExecutorService2.awaitTermination(7,TimeUnit.SECONDS);
        scheduledExecutorService2.shutdown();
    }
}
