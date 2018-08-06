package iterator;

public interface List {
    void add(Object object);
    Object get(int i);
    int size();
    Iterator iterator();
}
