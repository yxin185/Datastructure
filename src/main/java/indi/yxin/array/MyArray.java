package indi.yxin.array;

public class MyArray<E> {

    private E[] data;
    private int size;

    public MyArray(int capacity) {
        // java不支持泛型数组，需要进行类型转换
        data = (E[])new Object[capacity];
        size = 0;
    }

    // 无参构造，默认容量为10
    public MyArray() {
        this(10);
    }

    public MyArray(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0;i < arr.length;i++){
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 在位置index处添加元素e
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 && index <=size");
        }

        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1;i >= index;i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 是否包含元素e
    public boolean contains(E e) {
        for (int i = 0;i < size;i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果没找到，则返回-1
    public int find(E e) {
        for (int i = 0;i < size;i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    public void set(int index,E e) {

    }

    public E getFisrt() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 删除索引为index处的元素并返回该元素
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E ret = data[index];
        for (int i = index + 1;i < size;i++) {
            data[i - 1] = data[i];
        }
        size--;
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    // 删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }


    // 将数组变成newCapacity大小
    public void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0;i < size;i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
    // 交换索引为i,j的两个元素的位置
    public void swap(int i,int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal");

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array : size = %d, capacity = %d \n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if(i != size - 1) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
