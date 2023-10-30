package ru.sberbank.edu;



import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/***
 * this class may produce errors
 * throws IndexOutOfBoundsException
 */
public class CustomArrayImpl<T> implements CustomArray<T> {
    private final static int DEFAULT_SIZE = 5;
    private Object[] arrayElement;
    private int size;

    /***
     * an array is created with default capacity (5)
     */
    public CustomArrayImpl() {
        arrayElement = new Object[DEFAULT_SIZE];
    }

    /***
     * <p>
     * size does not equal capacity
     */
    @Override
    public int size() {
        return size;
    }

    /***
     * <p>
     * standard isEmpty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /***
     * <p>
     * when there is not enough space the array is increased by default
     *
     */
    @Override
    public boolean add(T item) {
        if (size == arrayElement.length){
            arrayElement = Arrays.copyOf(arrayElement, DEFAULT_SIZE + size);
        }
        arrayElement[size] = item;
        size++;
        return true;
    }

    @Override
    public boolean addAll(T[] items) {

        for (int i = 0; i < items.length; i++) {
            add(items[i]);
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<T> items) {
        Iterator<T> countriesIterator = items.iterator();
        while (countriesIterator.hasNext()) {
            add(countriesIterator.next());
        }

        return true;
    }

    @Override
    public boolean addAll(int index, T[] items) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (size + items.length > arrayElement.length) {
            arrayElement = Arrays.copyOf(arrayElement, size + items.length);
        }

        System.arraycopy(arrayElement, index, arrayElement, index + items.length, size - index);
        System.arraycopy(items, 0, arrayElement, index, items.length);

        size += items.length;
        return true;
    }

    @Override
    public T get(int index) {
        return (T) arrayElement[index];//todo так и не понял почему я должен явно указывать (Т)
    }

    @Override
    public T set(int index, Object item) {
        return (T) (arrayElement[index] = item);//todo так и не понял почему я должен явно указывать (Т)
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(arrayElement, index + 1, arrayElement, index, size - index - 1);
        arrayElement[--size] = null;
    }

    @Override
    public boolean remove(T item) {
        for (int index = 0; index < size; index++) {
            if (item.equals(arrayElement[index])) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(arrayElement[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        arrayElement = Arrays.copyOf(arrayElement, newElementsCount + size);
    }

    @Override
    public int getCapacity() {
        return arrayElement.length;
    }

    @Override
    public void reverse() {
        Object[] temp = new Object[size];
        for (int i = 0; i < size; i++) {
            temp[size - 1 - i] = arrayElement[i];
        }
        System.arraycopy(temp, 0, arrayElement, 0, size);
    }

    @Override
    public T[] toArray() {
        return (T[]) Arrays.copyOf(arrayElement, size);//todo так и не понял почему я должен явно указывать (Т)
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < size; i++) {
            sb.append(arrayElement[i]);
            if (i + 1 < size) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

