public class LeetCodeTest1 {

    private String name;

    private volatile int money;

    public LeetCodeTest1(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("swh");

        System.out.println("value=" + threadLocal.get());
        threadLocal.remove();
        System.out.println("value=" + threadLocal.get());
        System.out.println("");

    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
