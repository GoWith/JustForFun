package observe;

public interface Topic {
    void watch(Observer observer);

    void del(Observer observer);

    void notifyObserver();

    void operation();
}
