package Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Executor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable is run!");
            }
        };
        executorService.execute(task);

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return System.currentTimeMillis() % 100;
            }
        };

        FutureTask<Long> future = new FutureTask<Long>(callable);

        Thread thread = new Thread(future);
        thread.start();

        System.out.println(future.get());



        ExecutorService executorService1 = Executors.newCachedThreadPool();
        CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executorService1);
        for (int i = 0; i < 10; i++) {
            completionService.submit(() -> {
                Long result = System.currentTimeMillis() % 100;
                System.out.println(result);
                return result;
            });
        }
        Long sum = 0L;
        for (int i = 0; i < 10; i++) {
            sum+=completionService.take().get();
        }
        System.out.println(sum);
        executorService1.shutdown();
        executorService.shutdown();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("string"+i);
        }
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        });


        long count = list.stream().filter(l -> "String1".equals(1)).count();
        System.out.println(count);


        switch (1){
//            case 4:
//                System.out.println("4");
            default:
                System.out.println("default");
            case 2:
                System.out.println("2");
            case 1:
                System.out.println("1");
//            case 3:
//                System.out.println("3");
        }
    }
}

