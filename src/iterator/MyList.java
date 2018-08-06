package iterator;

public class MyList implements List {

    Object[] array = new Object[100];
    int size = 0;

    @Override
    public void add(Object object) {
        array[size++] = object;
    }

    @Override
    public Object get(int i) {
        return array[i];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator iterator() {
        return new ListIterator(this);
    }
}
