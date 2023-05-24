public interface MyCustomList<Object> extends MyCustomCollection {

    void add(int index, Object o);

    Object get(int index);

    Object remove(int index);

    boolean clear();

    boolean equals(MyCustomCollection o);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    boolean addAll(MyCustomCollection c);

    void addAll(int index, MyCustomCollection<Object> c);


}
