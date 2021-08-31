package com.training.circularbuffer;

//For review
public class FullCircularBufferException extends RuntimeException {
    public FullCircularBufferException() {
        super("Circular buffer is full!");
    }
}
