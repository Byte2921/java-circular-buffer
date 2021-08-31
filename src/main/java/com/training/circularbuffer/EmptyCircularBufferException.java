package com.training.circularbuffer;

//For review
public class EmptyCircularBufferException extends RuntimeException {
    public EmptyCircularBufferException() {
        super("Circular buffer is empty!");
    }
}
