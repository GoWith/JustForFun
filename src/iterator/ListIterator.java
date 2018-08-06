package iterator;

public class ListIterator implements Iterator{

    private int index = -1;
    private List list;

    public ListIterator(List list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {

        return index < list.size() - 1;
    }

    @Override
    public Object next() {
        if(index < list.size() - 1){
            index ++;
            return list.get(index);
        }
        return null;
    }
}
