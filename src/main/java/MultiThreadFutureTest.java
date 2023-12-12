import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadFutureTest {
    public static void main(String[] args){
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        List<Integer> b = new ArrayList<>();
        b.add(4);
        b.add(5);
        System.out.println("yes");
        try {
            System.out.println(getTotal(a, b));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getTotal(List<Integer> a, List<Integer> b) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        Future<Integer> future1 = es.submit(() -> {
            int r = 0;
            for(int num : a){
                r += num;
            }
            TimeUnit.SECONDS.sleep(3);
            System.out.println("future1: " + r);
            return r;
        });

        Future<Integer> future2 = es.submit(() -> {
            int r = 0;
            for (int num : b) {
                r += num;
            }
            TimeUnit.SECONDS.sleep(6);
            System.out.println("future2: " + r);
            return r;
        });
        es.shutdown();

        int r = 0;
        for(int num : b){
            r += num;
        }
        return r + future1.get() + future2.get();
    }
}
