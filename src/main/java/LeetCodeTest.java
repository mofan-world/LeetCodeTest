
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LeetCodeTest {
    public static void main(String[] args) throws IOException {
        InputStream is = LeetCodeTest.class.getClassLoader().getResourceAsStream("test.txt");

        try (FileInputStream fis = (FileInputStream) is) {
            int data;
            while ((data = fis.read()) != -1) {
                System.out.print((char) data); // 逐字节读取
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content =
                Files.readString(Path.of(LeetCodeTest.class.getClassLoader().getResource("test.txt").getPath()));
        System.out.println(content);
//        threadPoolUtil();
    }

    public String clearStars(String s) {
        Deque<Integer>[] cnt = new Deque[26];
        for (int i = 0; i < 26; i++) {
            cnt[i] = new ArrayDeque<>();
        }
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            if (arr[i] != '*') {
                cnt[arr[i] - 'a'].push(i);
            } else {
                for (int j = 0; j < 26; j++) {
                    if (!cnt[j].isEmpty()) {
                        arr[cnt[j].pop()] = '*';
                        break;
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (char c : arr) {
            if (c != '*') {
                ans.append(c);
            }
        }
        return ans.toString();

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
