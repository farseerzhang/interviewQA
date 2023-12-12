import java.util.concurrent.*;

public class MultiThreadFutureTest2 {
    public static void main(String[] args){
        System.out.println("开始");
        try {
            System.out.println(getTotal(1000));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getTotal(final int a) throws ExecutionException, InterruptedException {
//        ExecutorService es = Executors.newCachedThreadPool();  //线程池,不推荐这种方法，因为偶会创建过多线程 OOM

        //ThreadPoolExecutor 这种方式更直观，推荐
        ThreadPoolExecutor es = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3), new ThreadPoolExecutor.DiscardOldestPolicy());

        Future<Integer> future1 = es.submit(() -> {
            int r = 0;
            for (int i = 0; i <= a; i += 2){
                r += i;
            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println("future1: " + r);
            return r;
        });

        Future<Integer> future2 = es.submit(() -> {
            int r = 0;
            for (int i = 1; i <= a; i += 2){
                r += i;
            }
            TimeUnit.SECONDS.sleep(4);
            System.out.println("future2: " + r);
            return r;
        });

        Future<Integer> future3 = es.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("future3: ");
            return 88;
        });

        Future<Integer> future4 = es.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("future4: ");
            return 88;
        });

        es.shutdown();

        int r = 0;
        return r + future1.get() + future2.get() + future3.get() + future4.get();
    }
}
