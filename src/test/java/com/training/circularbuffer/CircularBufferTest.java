package com.training.circularbuffer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//For review
class CircularBufferTest {

    private final CircularBuffer<Integer> buffer = new CircularBuffer<>(4, Integer.class);

    @Test
    void checkBufferHeadPositionAfterAddingOneElement() {
        buffer.put(2021);
        assertEquals(0, buffer.getWriteHead());
    }

    @Test
    void checkIfWritingOnTailReturnsWithError() {
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.get();
        buffer.put(4);
        buffer.put(5);
        Assertions.assertThrows(FullCircularBufferException.class, () -> {
            buffer.put(6);
        });

    }

    @Test
    void checkIfGettingTheNextElementFromBufferIsCorrectValue() {
        buffer.put(2021);
        assertEquals(2021, buffer.get());
    }

    @Test
    void checkIfReadingAheadReturnsWithError() {
        buffer.put(1);
        buffer.put(2);
        buffer.get();
        buffer.get();
        Assertions.assertThrows(EmptyCircularBufferException.class, buffer::get);
    }

    @Test
    void checkIfBufferIsEqualWithSameArray() {
        buffer.put(1);
        buffer.put(2);
        assertArrayEquals(new Integer[]{1, 2}, buffer.toArray());
    }

    @Test
    void checkIfBufferIsEqualWithSameList() {
        buffer.put(1);
        buffer.put(2);
        assertEquals(Arrays.asList(1, 2), buffer.asList());
    }

    @Test
    void checkIfAddingGivenListToBufferThenReturnsTheSameList() {
        buffer.addAll(Arrays.asList(1, 2));
        assertEquals(Arrays.asList(1, 2), buffer.asList());
    }

    @Test
    void checkIfAddingTooLargeListToBufferReturnsWithError() {
        buffer.put(1);
        buffer.put(2);
        buffer.get();
        Assertions.assertThrows(FullCircularBufferException.class, () -> {
            buffer.addAll(Arrays.asList(3, 4, 5, 6));
        });
    }

    @Test
    void checkIfSortingBufferReturnsSameResultAsTestArray() {
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        buffer.put(1);
        Comparator<Integer> bufferComparator = Comparator.comparing(Integer::intValue);
        buffer.sort(bufferComparator);
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, buffer.toArray());
    }

    @Test
    void checkIfIsEmptyReturnsTrueAfterMovingTheTailAheadOfHead() {
        buffer.put(1);
        buffer.put(2);
        buffer.get();
        buffer.get();
        assertTrue(buffer.isEmpty());
    }
}