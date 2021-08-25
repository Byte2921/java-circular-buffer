package com.training.circularbuffer;

public class EmptyCircularBufferException extends RuntimeException {
    public EmptyCircularBufferException() {
        super("Circular buffer is empty!");
    }
}
