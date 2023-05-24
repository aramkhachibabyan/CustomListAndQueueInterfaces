import java.util.NoSuchElementException;

public class MyCustomLinkedList implements MyCustomList, MyCustomDeque {

    private Node first;
    private Node last;
    private int size = 0;

    public MyCustomLinkedList() {

    }

    public MyCustomLinkedList(MyCustomCollection c) {
        this();
        addAll(c);
    }

    @Override
    public void addFirst(Object e) {
        Node node = new Node();
        node.setValue(e);
        if (size == 0) {
            first = node;
            last = node;
            size++;
        } else {
            node.setNext(first);
            first.setPrevious(node);
            first = node;
            size++;
        }
    }

    @Override
    public void addLast(Object e) {
        add(e);
    }

    @Override
    public boolean add(Object o) {
        Node node = new Node();
        node.setValue(o);
        if (size == 0) {
            first = node;
            last = node;
            size++;
            return true;
        } else {
            node.setPrevious(last);
            last.setNext(node);
            last = node;
            size++;
            return true;
        }
    }

    @Override
    public void add(int index, Object o) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = new Node();
        node.setValue(o);
        MyListNodeIterator myListIterator = new MyListNodeIterator();
        Node temp = null;
        for (int i = 0; i < index; i++) {
            if (myListIterator.hasNext()) {
                temp = myListIterator.next();
            }
        }
        if (size == 0) {
            first = node;
            last = node;
            size++;
        } else if (index == 0) {
            node.setPrevious(null);
            node.setNext(first);
            first.setPrevious(node);
            first = node;
            size++;
        } else if (index == size - 1) {
            node.setNext(null);
            node.setPrevious(last);
            last.setNext(node);
            last = node;
            size++;
        } else {
            node.setNext(temp.getNext());
            node.setPrevious(temp);
            temp.setNext(node);
            temp.getNext().setPrevious(node);
            size++;
        }

    }

    @Override
    public boolean addAll(MyCustomCollection c) {
        for (int i = 0; i < c.size(); i++) {
            add(c.get(i));
        }
        return true;
    }

    @Override
    public void addAll(int index, MyCustomCollection c) {
        for (int i = c.size() - 1; i >= 0; i--) {
            add(index, c.get(i));
        }
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyListNodeIterator myListIterator = new MyListNodeIterator();
        Node temp = null;
        for (int i = 0; i <= index; i++) {
            if (myListIterator.hasNext()) {
                temp = myListIterator.next();
            }
        }
        if (index == 0) {
            temp.getNext().setPrevious(null);
            first = temp.getNext();
        } else if (index == size - 1) {
            temp.getPrevious().setNext(null);
            last = temp.getPrevious();
        } else {
            temp.getNext().setPrevious(temp.getPrevious());
            temp.getPrevious().setNext(temp.getNext());
        }
        size--;
        return temp;
    }

    @Override
    public Object remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Object temp = remove(0);
        return temp;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.getNext()) {
                if (x.getValue() == null) {
                    remove(indexOf(x));
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getValue())) {
                    remove(indexOf(x));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object removeFirst() {
        return remove();
    }

    @Override
    public Object removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Object temp = remove(size - 1);
        return temp;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            for (Node x = last; x != null; x = x.getPrevious()) {
                if (x.getValue() == null) {
                    remove(lastIndexOf(x));
                    return true;
                }
            }
        } else {
            for (Node x = last; x != null; x = x.getPrevious()) {
                if (o.equals(x.getValue())) {
                    remove(lastIndexOf(x));
                    return true;
                }
            }
        }
        return false;
    }


    public Node getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first;
    }

    public Node getLast() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return last;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyListNodeIterator myListIterator = new MyListNodeIterator();
        Node temp = null;
        for (int i = 0; i <= index; i++) {
            if (myListIterator.hasNext()) {
                temp = myListIterator.next();
            }
        }
        return temp.getValue();
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean clear() {
        first = null;
        first.setNext(null);
        first.setPrevious(null);
        last = null;
        return true;
    }

    @Override
    public boolean offer(Object e) {
        return add(e);
    }

    @Override
    public boolean offerFirst(Object e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(Object e) {
        addLast(e);
        return true;
    }

    @Override
    public Object poll() {
        return removeFirst();
    }

    @Override
    public Object pollFirst() {
        return removeFirst();
    }

    @Override
    public Object pollLast() {
        return removeLast();
    }

    @Override
    public Object element() {
        return getFirst();
    }

    @Override
    public Object peekFirst() {
        return peek();
    }

    @Override
    public Object peekLast() {
        if (size == 0) {
            return null;
        }
        return last;
    }

    @Override
    public Object peek() {
        if (size == 0) {
            return null;
        }
        return first.getValue();
    }

    @Override
    public void push(Object e) {
        addFirst(e);
    }

    @Override
    public Object pop() {
        return removeFirst();
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.getNext()) {
                if (x.getValue() == null) {
                    return true;
                }
            }
        } else {
            for (Node x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object[] toArray(MyCustomCollection e) {

        Object[] o = new Object[size];
        for (int i = 0; i < size; i++) {
            o[i] = e.get(i);
        }

        return o;
    }

    @Override
    public boolean equals(MyCustomCollection o) {
        boolean t = true;
        int i = 0;
        for (Node x = first; x != null; x = x.getNext()) {
            if (o.get(i).equals(x)) {
                t = false;
                break;
            }
            i++;
        }
        return t;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.getNext()) {
                if (x.getValue() == null)
                    return index;
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.getNext()) {
                if (o.equals(x.getValue()))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node x = last; x != null; x = x.getPrevious()) {
                index--;
                if (x.getValue() == null)
                    return index;
            }
        } else {
            for (Node x = last; x != null; x = x.getPrevious()) {
                index--;
                if (o.equals(x.getValue()))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    class MyListDescendingIterator implements MyCustomIterator<Object> {
        Node cursor;
        boolean isLast = true;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (size > 0 && isLast) {
                cursor = last;
                isLast = false;
                return true;
            }
            if (cursor.getPrevious() != null) {
                cursor = cursor.getPrevious();
                return true;
            }
            cursor = null;
            return false;
        }

        @Override
        public Object next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            return cursor.getValue();
        }
    }

    class MyListNodeIterator implements MyCustomIterator<Node> {
        Node cursor;
        boolean isFirst = true;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (size > 0 && isFirst) {
                cursor = first;
                isFirst = false;
                return true;
            }
            if (cursor.getNext() != null) {
                cursor = cursor.getNext();
                return true;
            }
            cursor = null;
            return false;
        }

        @Override
        public Node next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            return cursor;
        }
    }

    class MyLinkedListIterator implements MyCustomIterator<Object> {
        Node cursor;
        boolean isLast = true;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (size > 0 && isLast) {
                cursor = last;
                isLast = false;
                return true;
            }
            if (cursor.getPrevious() != null) {
                cursor = cursor.getPrevious();
                return true;
            }
            cursor = null;
            return false;
        }

        @Override
        public Object next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            return cursor.getValue();
        }
    }


    class MyListDescendingNodeIterator implements MyCustomIterator<Node> {
        Node cursor;
        boolean isLast = true;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (size > 0 && isLast) {
                cursor = last;
                isLast = false;
                return true;
            }
            if (cursor.getPrevious() != null) {
                cursor = cursor.getPrevious();
                return true;
            }
            cursor = null;
            return false;
        }

        @Override
        public Node next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            return cursor;
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += this.get(i).toString();
        }
        return str;
    }

    public static class Node {
        private Node next;
        private Node previous;
        private Object value;

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setNext(Node next) {
            this.next = next;
        }


        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
