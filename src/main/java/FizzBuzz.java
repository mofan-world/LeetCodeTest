import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private int x = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (x <= n) {
            synchronized (this) {
                if (x % 3 == 0 && x % 5 != 0) {
                    printFizz.run();
                    x++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (x <= n) {
            synchronized (this) {
                if (x % 3 != 0 && x % 5 == 0) {
                    printBuzz.run();
                    x++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (x <= n) {
            synchronized (this) {
                if (x % 3 == 0 && x % 5 == 0) {
                    printFizzBuzz.run();
                    x++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (x <= n) {
            synchronized (this) {
                if (x % 3 != 0 && x % 5 != 0) {
                    printNumber.accept(x);
                    x++;
                    notifyAll();
                } else {
                    wait();
                }
            }
        }
    }
}
