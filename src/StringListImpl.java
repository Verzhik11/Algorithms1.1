import exceptions.IncorrectIndexException;
import exceptions.NullItemException;
import exceptions.PlaceIsBusyException;

import java.rmi.NotBoundException;
import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] repository;
    private int size;

    public StringListImpl(int initSize) {
        this.repository = new String[initSize];
        size = 0;
    }


    @Override
    public String add(String item) {
        validateItem(item);
        repository[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateItem(item);
        validateIndex(index);
        if (index == size) {
            resize(size + 1);
        }
        for (int i = size; i > index; i--) {
            repository[i] = repository[i - 1];
        }
        repository[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);
        repository[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new RuntimeException("Удаляемый объект отсутствует");
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String result = repository [index];
        for (int i = index + 1; i < size; i++) {
            repository [i -1] = repository [i];
            }
        size--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        return repository[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullItemException("Отсутствует входной параметр");
        }
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(repository, 0, size, null);
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(repository, repository.length);
    }

    public void validateItem(String item) {
        if (item == null) {
            throw new NullItemException("Отсутствует входной параметр");
        }
    }

    public void validateIndex(int index) {
        if (index < 0 || index >= repository.length) {
            throw new IncorrectIndexException("Такой ячейки не существует");
        }
    }


    @Override
    public String toString() {
        return "StringListImpl{" +
                "repository=" + Arrays.toString(repository) +
                '}';
    }

    public void resize(int newSize) {
        int size1 = size * 2;
        size1 = Math.max(size1, newSize);
        String[] newRepository = Arrays.copyOf(repository, size1);
        repository = newRepository;

    }
}
