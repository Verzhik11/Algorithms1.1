import exceptions.IncorrectIndexException;
import exceptions.NullItemException;
import exceptions.PlaceIsBusyException;

import java.rmi.NotBoundException;
import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] repository;
    private int size;

    public IntegerListImpl(int initSize) {
        this.repository = new Integer[initSize];
        size = 0;
    }


    @Override
    public Integer add(Integer item) {
        validateItem(item);
        repository[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        repository[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new RuntimeException("Удаляемый объект отсутствует");
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer result = repository[index];
        for (int i = index + 1; i < size; i++) {
            repository[i - 1] = repository[i];
        }
        size--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        sort();
        return binarSearch(item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (repository[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        return repository[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
    public Integer[] toArray() {
        return Arrays.copyOf(repository, repository.length);
    }

    public void validateItem(Integer item) {
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
        return "IntegerListImpl{" +
                "repository=" + Arrays.toString(repository) +
                '}';
    }

    public void resize(int newSize) {
        int size1 = size * 2;
        size1 = Math.max(size1, newSize);
        Integer[] newRepository = Arrays.copyOf(repository, size1);
        repository = newRepository;

    }

    private void sort() {
        for (int i = 1; i < size; i++) {
            int temp = repository[i];
            int j = i;
            while (j > 0 && repository[j - 1] >= temp) {
                repository[j] = repository[j - 1];
                j--;
            }
            repository[j] = temp;
        }
    }

    private boolean binarSearch(Integer item) {
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (repository[mid].compareTo(item) == 0) {
                return true;
            }
            if (repository[mid].compareTo(item) == 1) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}
