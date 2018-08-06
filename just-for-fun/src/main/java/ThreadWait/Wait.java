package ThreadWait;

public class Wait {
    public static void main(String[] args) {
        WaitThread t1 = new WaitThread(null, "t1");
        WaitThread t2 = new WaitThread(t1, "t2");
        WaitThread t3 = new WaitThread(t2, "t3");
        t1.before = t3;

        t1.start();
        t2.start();
        t3.start();
    }

}
class Lock{
    public static String lockFlag="t";
}

class WaitThread extends Thread {
    final static public Lock lock = new Lock();
    WaitThread before;
    String name;
    Integer count = 0;

    public WaitThread(WaitThread before, String name) {
        this.before = before;
        this.name = name;
    }

    @Override
    public void run() {
        if (name.equals("t1")) {
            System.out.println(count++ + name);
            synchronized (lock) {
                lock.lockFlag = name;
                lock.notifyAll();
            }
        }
        while (true) {

                if (lock.lockFlag.equals(before.name)) {
                    System.out.println(count++ + name);
                    synchronized (lock) {
                        lock.lockFlag = name;
                        lock.notifyAll();
                    }
                } else {
                    try {
                        synchronized (lock) {
                            lock.notifyAll();
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }

    }
}
