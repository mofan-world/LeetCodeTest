import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode2 {

    private static final int coreThreadNum = Runtime.getRuntime().availableProcessors();

    private static final int maxPoolThreadNum = coreThreadNum * 2;

    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(100);

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(coreThreadNum, maxPoolThreadNum, 3L,
            TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        System.out.println("Hello World!");
        for (int i = 0; i < 200; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    static class CustomThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private AtomicInteger threadNumber = new AtomicInteger(1);

        public CustomThreadFactory(String s) {
            namePrefix = s;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, namePrefix + threadNumber.getAndIncrement());
        }


    }
}
