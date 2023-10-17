package core;

import java.util.concurrent.*;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //runAsync();
        Future f = calculateAsync();
        System.out.println("Calculate Sync result:-->"+f.get());
    }

    private static void runAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                CompletableFutureDemo::run
        ).thenApply((r)->{
            System.out.println("Getting result:-->"+r);
            return r+" Applied again!";
        });
        System.out.println("Output:-->"+completableFuture.getNow("Default Value Returned!"));
    }

    private static Future<Double> calculateAsync() throws InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();

        ExecutorService service = Executors.newCachedThreadPool();
        Future<Double> future = service.submit(()->{
            TimeUnit.SECONDS.sleep(1);
            return 10.9;
        });
        service.shutdown();
        boolean isTerminated = service.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("Await Termination Result:-->"+isTerminated);

        if(future.isDone()){
            return future;
        }else{
            completableFuture.complete(9.99);
            return completableFuture;
        }
    }

    private static String run() {
        System.out.println("Inside completable future:"+Thread.currentThread().getName());
        return "Result";
    }
}
