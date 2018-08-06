package Thread.join;

public class ThreadJoin {

    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("**");
            }
        };
        JoinThread joinThread = new JoinThread(thread);
        JoinThread joinThreadAfter = new JoinThread(joinThread);

        thread.start();
        joinThread.start();
        joinThreadAfter.start();
        try {
            joinThreadAfter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
class JoinThread extends Thread{
    Thread beforeThread = null;
    public JoinThread(Thread beforeThread){
        this.beforeThread = beforeThread;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +"start");
        if(beforeThread != null){
            try {
                beforeThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() +"end");
    }
}

