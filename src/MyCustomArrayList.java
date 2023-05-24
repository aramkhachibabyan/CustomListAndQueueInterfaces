import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyCustomArrayList implements MyCustomList<Object> {

    private int capacity = 10;
    private int size = 0;
    private Object[] data;

    public MyCustomArrayList() {
        this.data = new Object[capacity];
    }

    public MyCustomArrayList(int capacity) {
        this.capacity = capacity;
        this.data = new Object[capacity];
    }

    @Override
    public boolean add(Object o) {
        if (size == capacity) {
            capacity = capacity * 3 / 2 + 1;
            data = Arrays.copyOf(data, capacity);
        }
        data[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int k = 0;
        boolean t = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == o) {
                k = i;
                t = true;
                break;
            }
        }
        remove(k);
        return t;
    }

    @Override
    public void add(int index, Object o) {
        if (size == capacity) {
            capacity = capacity * 3 / 2 + 1;
            data = Arrays.copyOf(data, capacity);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = o;
        size++;
    }

    @Override
    public boolean addAll(MyCustomCollection c) {
        if (size + c.size() >= capacity) {
            capacity = capacity * 3 / 2 + c.size();
            data = Arrays.copyOf(data, capacity);
        }
        for (int i = 0; i < c.size(); i++) {
            data[size] = c.get(i);
            size++;
        }
        return true;
    }

    @Override
    public void addAll(int index, MyCustomCollection<Object> c) {
        if (size + c.size() >= capacity) {
            capacity = capacity * 3 / 2 + c.size() + 100;
            data = Arrays.copyOf(data, capacity);
        }
        for (int i = index; i < size; i++) {
            data[i + c.size()] = data[i];
        }
        for (int i = index; i < size; i++) {
            data[i] = c.get(i - index);
        }
        size += c.size();
    }

    @Override
    public Object get(int index) {
        return data[index];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object removed = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return removed;
    }


    @Override
    public boolean clear() {
        for (int i = 0; i < size; i++) {
            data[i] = 0;
        }
        size = 0;
        return true;
    }

    @Override
    public boolean equals(MyCustomCollection o) {
        boolean t = true;
        for (int i = 0; i < size; i++) {
            if (data[i] != o.get(i)) {
                t = false;
                break;
            }
        }
        return t;
    }

    @Override
    public MyCustomArrayList clone() {
        MyCustomArrayList myCustomArrayList = new MyCustomArrayList();
        for (int i = 0; i < size; i++) {
            myCustomArrayList.add(data[i]);
        }
        return myCustomArrayList;
    }

    public boolean contains(Object o) {
        boolean t = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == o)
                t = true;
        }
        return t;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity >= size && minCapacity <= capacity) {
            capacity = minCapacity;
        }
    }


    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] == o) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = size - 1; i >= 0; i++) {
            if (data[i] == o) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    public void trimToSize() {
        capacity = size;
    }

    public Object[] toArray(MyCustomCollection e) {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = data[i];
        }
        return array;
    }

    public void set(int index, Object o) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = o;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += this.data[i].toString();
        }
        return str;
    }

    class MyArrayListIterator implements MyCustomIterator<Object> {
        Object cursor;
        boolean isLast = true;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (size > 0 && isLast) {
                cursor = data[0];
                isLast = false;
                return true;
            }
            if (data[indexOf(cursor) + 1] != null) {
                cursor = data[indexOf(cursor) + 1];
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
            return cursor;
        }
    }

    class MyArrayListDescendingIterator implements MyCustomIterator<Object> {
        Object cursor;
        boolean isLast = true;

        @Override
        public boolean hasNext() {
            if (size == 0) {
                return false;
            }
            if (size > 0 && isLast) {
                cursor = data[size - 1];
                isLast = false;
                return true;
            }
            if (data[indexOf(cursor) - 1] != null) {
                cursor = data[indexOf(cursor) - 1];
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
            return cursor;
        }
    }


}
