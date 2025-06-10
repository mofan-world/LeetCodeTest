import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode1 {

    private static final int coreThreadNum = Runtime.getRuntime().availableProcessors();

    private static final int maxPoolThreadNum = coreThreadNum * 2;

    public static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(10);

    public static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(coreThreadNum, maxPoolThreadNum, 3L,
            TimeUnit.SECONDS, workQueue, new CustomThreadFactory("worker-test-"),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        for (int i = 0; i < 20; i++) {
            int fn = i;
            threadPool.execute(() -> {
                System.out.println("[" + fn + "]队列数量=" + workQueue.size() + "------线程名称=" + Thread.currentThread().getName());
            });
        }
        threadPool.shutdown();
        System.out.println((int) 'a');
        System.out.println((int) 'A');
    }

    public int maxDifference(String s) {
        int maxj = 0, minj = 100, maxo = 0, mino = 100, cur = 0;
        for (int i = 0; i < 26; i++) {
            cur = (int) countChar(s, (char) ('a' + i));
            if (cur % 2 == 1) {
                maxj = Math.max(maxj, cur);
                minj = Math.min(minj, cur);
            }
            if (cur != 0 && cur % 2 == 0) {
                maxo = Math.max(maxo, cur);
                mino = Math.min(mino, cur);
            }
        }
        return Math.max(minj - maxo, maxj - mino);
    }

    public static long countChar(String str, char target) {
        return str.chars()
                .filter(c -> c == target).count();
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

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < n; i++) {
            ans.add(cur);
            if (cur * 10 < n) {
                cur *= 10;
            } else {
                while (cur % 10 == 9 || cur + 1 > n) {
                    cur /= 10;
                }
                cur++;
            }
        }
        return ans;
    }
}
