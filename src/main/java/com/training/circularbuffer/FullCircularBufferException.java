package com.training.circularbuffer;

public class FullCircularBufferException extends RuntimeException {
    public FullCircularBufferException() {
        super("Circular buffer is full!");
    }
}
