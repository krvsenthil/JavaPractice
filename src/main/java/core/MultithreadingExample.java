package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadingExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //threadPoolExample();
        threadListExample();
    }

    private static void threadListExample() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Integer>> threadList = new ArrayList<>(
                Arrays.asList(
                        MultithreadingExample::execute,
                        MultithreadingExample::execute,
                        MultithreadingExample::execute
                )
        );
        List<Future<Integer>> futureList = executorService.invokeAll(threadList, 2, TimeUnit.SECONDS);
        Integer integerFuture = executorService.invokeAny(threadList, 2, TimeUnit.SECONDS);
        System.out.println("InvokeAny:-->"+integerFuture);

        futureList.stream().map(f-> {
            try {
                return f.get();
            }catch (InterruptedException | ExecutionException ie){
                ie.printStackTrace();
            }
            return "";
        }).forEach(System.out::println);
        //Another way to get results
        for(Future<Integer> f : futureList){
            System.out.println("Future Result:-->"+Thread.currentThread().getName()+"-->"+f.get());
        }
        executorService.shutdown();

        try{
            executorService.awaitTermination(2, TimeUnit.SECONDS);
        }catch (InterruptedException ie){
            System.out.println("Interrupted exception");
            executorService.shutdownNow();
        }
    }

    private static void threadPoolExample() throws ExecutionException, InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(MultithreadingExample::execute);
        Future<Integer> futureCallable = executorService
                .submit(MultithreadingExample::execute);
        System.out.println("Future Result:-->"+futureCallable.get());
        executorService.shutdown();
    }

    private static int execute() throws InterruptedException {
        int r = 1+1;
        //TimeUnit.SECONDS.sleep(4);
        return r;
    }
}
