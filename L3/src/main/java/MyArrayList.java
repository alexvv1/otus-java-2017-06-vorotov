import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List<T> {

    /**
     * Начальный размер списка
     */
    private static final int START_SIZE = 1;
    /**
     * Размер списка
     */
    private int size;
    /**
     * Массив хранящий элементы списка
     */
    private T[] arr = (T[]) new Object[START_SIZE];

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() <= 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        final T[] newArr = (T[]) new Object[this.size()];
        System.arraycopy(arr, 0, newArr, 0, this.size());
        return newArr;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) this.toArray();
    }

    @Override
    public boolean add(T t) {
        extendArr();
        arr[size++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size(); i++) {
            if (arr[i].equals(o)) {
                //Если не последний элемент
                if (i != this.size() - 1)
                    System.arraycopy(arr, i + 1, arr, i, this.size() - i - 1);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        arr = (T[]) new Object[START_SIZE];
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        extendArr();
        arr[index] = element;
        return element;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ElementsIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * Итератор
     */
    private class ElementsIterator implements ListIterator<T> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return MyArrayList.this.size() > cursor;
        }

        @Override
        public T next() {
            return MyArrayList.this.arr[cursor++];
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            int setCursor = cursor - 1;
            if(setCursor < 0 ) {
                throw new IllegalArgumentException("Position set less zero");
            }

            MyArrayList.this.arr[setCursor] = t;
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }


    private void checkIndex(int index) {
        if (index < 0 && index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    private void extendArr() {
        if (arr.length == size) {
            final T[] oldArr = arr;
            arr = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldArr, 0, arr, 0, oldArr.length);
        }
    }
}
