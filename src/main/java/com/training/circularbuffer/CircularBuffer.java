package com.training.circularbuffer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CircularBuffer<T> {

    private final T[] data;
    private final int size;
    private int readTail;
    private int writeHead;

    private final static int DEFAULT_SIZE = 8;

    public CircularBuffer(int size, Class<T> type) {
        this.size = size <= 0 ? DEFAULT_SIZE : size;
        this.data = (T[]) java.lang.reflect.Array.newInstance(type, this.size);
        this.readTail = 0;
        this.writeHead = -1;
    }

    public int getSize() {
        return size;
    }

    public int getReadTail() {
        return readTail;
    }

    public int getWriteHead() {
        return writeHead;
    }

    public void put(T t) throws FullCircularBufferException {
        if (!checkIfBufferIsFull()) {
            data[++writeHead % size] = t;
        } else {
            throw new FullCircularBufferException();
        }
    }

    public T get() throws EmptyCircularBufferException {
        if (!isEmpty()) {
            return data[readTail++ % size];
        } else {
            throw new EmptyCircularBufferException();
        }
    }

    public T[] toArray() throws EmptyCircularBufferException {
        int fromRange = (readTail % size);
        int toRange = fromRange > (writeHead % size + 1) ? size : (writeHead % size + 1);
        if (!isEmpty()) {
            return (T[]) Arrays.copyOfRange(this.data, fromRange, toRange);
        } else {
            throw new EmptyCircularBufferException();
        }
    }

    public List<T> asList() throws EmptyCircularBufferException {
        if (!isEmpty()) {
            return Arrays.stream(this.toArray()).toList();
        } else {
            throw new EmptyCircularBufferException();
        }
    }

    public void AddAll(List<? extends T> toAdd) throws FullCircularBufferException {
        for (T item : toAdd) {
            if (!isEmpty()) {
                put(item);
            } else {
                throw new FullCircularBufferException();
            }
        }
    }

    public void sort(Comparator<? super T> comparator) throws EmptyCircularBufferException {
        if (!isEmpty()) {
            Arrays.sort(this.data, comparator);
        } else {
            throw new EmptyCircularBufferException();
        }
    }

    public boolean isEmpty() {
        return writeHead < readTail;
    }

    private boolean checkIfBufferIsFull() {
        return (writeHead - readTail) + 1 == size;
    }

}
