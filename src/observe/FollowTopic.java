package observe;

import java.util.ArrayList;
import java.util.List;

public class FollowTopic implements Topic{

    List<Observer> observers = new ArrayList<>();

    @Override
    public void watch(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void del(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.OnMessage();
        }
    }

    @Override
    public void operation() {
        System.out.println("everything about follow is ok");
        notifyObserver();
    }
}
