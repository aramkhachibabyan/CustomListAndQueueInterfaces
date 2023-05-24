public interface MyCustomDeque extends MyCustomQueue {
    void addFirst(Object e);

    void addLast(Object e);

    boolean offerFirst(Object e);

    boolean offerLast(Object e);

    Object removeFirst();

    Object removeLast();

    Object pollFirst();

    Object pollLast();

    Object getFirst();

    Object getLast();

    Object peekFirst();

    Object peekLast();

    boolean removeFirstOccurrence(Object o);

    boolean removeLastOccurrence(Object o);

    boolean add(Object e);

    boolean offer(Object e);

    Object remove();

    Object poll();

    Object element();

    Object peek();

    boolean addAll(MyCustomCollection c);

    void push(Object e);

    Object pop();

    boolean remove(Object o);

    boolean contains(Object o);

    int size();

}
