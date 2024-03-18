package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_LENGTH = 10;
    private T[] array;
    private int size;

    ArrayList() {
        array = (T[]) new Object[ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        if (size != array.length) {
            array[size] = value;
            size++;
        } else {
            resize();
            array[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
        if (size == array.length) {
            array[index] = value;
            size++;
        } else if (index < size) {
            for (int i = 0; i < size - 1; i++) {
                array[i + 1] = array[i];
            }
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        int arraySize = size;
        for (int i = arraySize; i < arraySize + list.size(); i++) {
            if (size == array.length) {
                resize();
            }
            array[i] = list.get(i - arraySize);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("element with such index does not exist");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("element with such index does not exist");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("element with such index does not exist");
        }
        T temp = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i + 1] = array[i];
        }
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if(Objects.equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in data " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private T[] resize() {
        int newLength = array.length + (array.length >> 1);
        return array = Arrays.copyOf(array, newLength);
    }
}
