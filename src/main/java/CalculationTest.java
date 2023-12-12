import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CalculationTest {
    private static final int N = 1000000000;

    public static void main(String[] args) {
        long startTime, endTime;

        // 测试 CompletableFuture
        startTime = System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 异步计算逻辑
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                sum += i;
            }
            return sum;
        });
        try {
            future.get(); // 等待计算完成
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("CompletableFuture执行时间：" + (endTime - startTime) + " 毫秒");

        // 测试普通方法
        startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("普通方法执行时间：" + (endTime - startTime) + " 毫秒");
    }
}