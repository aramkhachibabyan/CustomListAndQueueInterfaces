public interface MyCustomCollection<Object> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray(MyCustomCollection e);

    boolean add(Object e);

    boolean remove(Object o);

    boolean addAll(MyCustomCollection c);

    boolean clear();

    public Object get(int index);
}
