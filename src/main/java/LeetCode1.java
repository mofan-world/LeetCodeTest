import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode1 {

    private static final int coreThreadNum = Runtime.getRuntime().availableProcessors();

    private static final int maxPoolThreadNum = coreThreadNum * 2;

    public static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(coreThreadNum, maxPoolThreadNum, 3L,
            TimeUnit.SECONDS, workQueue, new CustomThreadFactory("worker-test-"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        System.out.println("Hello World!");
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException" + e);
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
    }

    private static class CustomThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger counter = new AtomicInteger(1);

        public CustomThreadFactory(String s) {
            this.namePrefix = s;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, namePrefix + counter.getAndIncrement());
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            thread.setUncaughtExceptionHandler((t, e) -> System.err.println("Thread " + t.getName() + " failed: " + e));
            return thread;
        }
    }
}
