package command;

public class ShardingTwoReceiver implements Receiver {
    @Override
    public void action() {
        System.out.println("sharding two action");
    }
}
