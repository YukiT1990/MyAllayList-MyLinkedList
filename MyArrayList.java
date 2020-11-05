package collections.list.assignment;


import java.util.*;

public class MyArrayList implements List, RandomAccess {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elementData;
    private int size;

    public MyArrayList() {
        // TODO: Implement Me
        elementData = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList(Collection c) {
        elementData = c.toArray();
    }

    @Override
    public int size() {
        // TODO: Implement Me
        int numberOfElementsInTheList = 0;
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] != null) {
                numberOfElementsInTheList += 1;
            }
        }
        return numberOfElementsInTheList;
    }

    @Override
    public boolean isEmpty() {
        // TODO: Implement Me
        if (elementData.length == 0) {
            return true;
        } else {
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] != null) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public boolean contains(Object o) {
        // TODO: Implement Me
        if (elementData.length == 0) {
            return false;
        } else {
            for (int i = 0; i < elementData.length; i++) {
                if (elementData[i] == o) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public Iterator iterator() {
        // DO NOT NEED TO IMPLEMENT
        return null;
    }

    @Override
    public Object[] toArray() {
        // TODO: Implement Me
        Object Array[] = new Object[size];
        for (int i = 0; i < size; i++) {
            Array[i] = elementData[i];
        }
        return Array;
    }

    @Override
    public Object[] toArray(Object[] a) {
        // DO NOT NEED TO IMPLEMENT
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        // TODO: Implement Me
        if (size == elementData.length) {
            elementData = grow(elementData.length);
        }
        elementData[size] = o;
        size++;
        return true;
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, minCapacity + minCapacity / 2);
    }

    @Override
    public boolean remove(Object o) {
        // TODO: Implement Me
        // find the index of Object o; (linear search)
        int index = -1;
        for(int i = 0; i < elementData.length; i++) {
            if (elementData[i] == o) {
                index = i;
            }
        }
        if (index == -1) {
            return false;
        }
        // shift all the elements after the index to left
        for (int i = index; i < elementData.length - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[elementData.length - 1] = null;
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO: Implement Me
        int count = 0;
        for (Object o : c) {
            for (Object d : elementData) {
                if (o == d) {
                    count +=1;
                    continue;
                }
            }
        }
        if (count == c.size()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO: Implement Me
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + c.size());
        }
        for (Object o : c) {
            elementData[size] = o;
            size++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        // TODO: Implement Me
        if (index < 0 || index >= elementData.length) {
            return false;
        }
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length + c.size());
        }
        int indexToAdd = index;
        for (Object o : c) {
            elementData[indexToAdd] = o;
            indexToAdd++;
            size++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        // TODO: Implement Me
        int initialSize = size;
        for (Object o : c) {
            int index = -1;
            for (int i = 0; i < elementData.length; i++) {
                if (o == elementData[i]) {
                    index = i;
                }
            }
            if (index == -1) {
                continue;
            }
            for (int i = index; i < elementData.length - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            elementData[elementData.length - 1] = null;
            size--;
        }
        if (initialSize != size) {
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO: Implement Me
        int initialSize = size;
        for (int i = 0; i < elementData.length; i++) {
            boolean present = false;
            for (Object o : c) {
                if (elementData[i] == o) {
                    present = true;
                }
            }
            if (!present) {
                for (int j = i; j < elementData.length - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[elementData.length - 1] = null;
                size--;
            }
        }
        if (initialSize != size) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        // TODO: Implement Me
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
    }

    @Override
    public Object get(int index) {
        // TODO: Implement Me
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elementData[index];
    }

    @Override
    public Object set(int index, Object element) {
        // TODO: Implement Me
        Object temp;
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else {
            temp = elementData[index];
            elementData[index] = element;
        }
        return temp;
    }

    @Override
    public void add(int index, Object element) {
        // TODO: Implement Me
        if (size == elementData.length) {
            elementData = grow(elementData.length);
            size++;
        }
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else {
            for (int i = elementData.length - 1; i > index; i--) {
                elementData[i] = elementData[i - 1];
            }
            elementData[index] = element;
        }

    }

    @Override
    public Object remove(int index) {
        // TODO: Implement Me
        Object temp;
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else {
            temp = elementData[index];
            for (int i = index; i < elementData.length - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            elementData[elementData.length - 1] = null;
            size--;
        }
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        // TODO: Implement Me
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO: Implement Me
        for (int i = elementData.length - 1; i >= 0; i--) {
            if (elementData[i] == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator listIterator() {
        // DO NOT NEED TO IMPLEMENT
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        // DO NOT NEED TO IMPLEMENT
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        // TODO: Implement Me
        if (fromIndex < 0|| toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        List<Object> sublist = new ArrayList<Object>();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(elementData[i]);
        }
        return sublist;
    }
}
