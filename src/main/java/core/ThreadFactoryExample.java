package core;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadFactoryExample {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4, new DefaultThreadFactoryExample());
        IntStream.range(0,10).forEach(i->{
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
        });
        System.out.println("Pool Size-->"+threadPoolExecutor.getPoolSize());
        System.out.println("Queue Size:-->"+threadPoolExecutor.getQueue().size());
        threadPoolExecutor.shutdown();
    }
}

class DefaultThreadFactoryExample implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "Thread Name:-->"+poolNumber.incrementAndGet());
    }
}
