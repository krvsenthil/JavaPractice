package core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo{
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        int count = 10000;
        List<Thread> allThreads = new ArrayList<>();
        for(int i = 0; i < count; i++){
            var t = new Thread(AtomicDemo::incrementCounter);
            //var t1 = new Thread(core.AtomicDemo::incrementCounter);
            allThreads.add(t);
            t.start();
        }
        for(Thread t : allThreads){
            t.join();
        }
        System.out.println(atomicInteger);
        //System.out.println(counter);
    }

    public static void atomicIncrementCounter(){
        atomicInteger.incrementAndGet();
    }
    //Atomic or synchronized
    public synchronized static void incrementCounter(){
        counter++;
    }

}
