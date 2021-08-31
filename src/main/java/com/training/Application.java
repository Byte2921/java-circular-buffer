package com.training;

import com.training.circularbuffer.CircularBuffer;

import java.util.Arrays;

//For review
public class Application {

    public static void main(String[] args) {
        CircularBuffer<Integer> buffer = new CircularBuffer<>(8, Integer.class);
        buffer.put(11);
    }
}
