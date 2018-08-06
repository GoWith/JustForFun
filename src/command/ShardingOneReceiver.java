package command;

public class ShardingOneReceiver implements Receiver {
    @Override
    public void action() {
        System.out.println("sharding one action");
    }
}
