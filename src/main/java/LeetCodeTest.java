import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LeetCodeTest {
    public static void main(String[] args) {
        threadPoolUtil();
    }

    public static void threadPoolUtil() {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                5,
                10,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 200; i++) {
            int finalI = i;
            tpe.execute(() -> {
                System.out.println("thread name " + finalI + " : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        tpe.shutdown();
    }
}
