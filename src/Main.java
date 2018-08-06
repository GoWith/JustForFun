import command.*;
import factory.StrategyFactory;
import iterator.Iterator;
import iterator.List;
import iterator.MyList;
import observe.CrmObserver;
import observe.FollowTopic;
import observe.PromotionObserver;
import observe.Topic;
import strategy.AwardStrategy;

public class Main {

    public static void main(String[] args) {
        AwardStrategy awardStrategy = StrategyFactory.createdStrategy("pop_coupon");
        awardStrategy.award();
        System.out.println("Hello World!");


        Receiver receiver = new ShardingOneReceiver();
        Command command = new QueryCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.action();


        Topic topic = new FollowTopic();
        topic.watch(new CrmObserver());
        topic.watch(new PromotionObserver());


        List list = new MyList();
        for (int i = 0; i < 80; i++) {
            list.add("object"+i);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
