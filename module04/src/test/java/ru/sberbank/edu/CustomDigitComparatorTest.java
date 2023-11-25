package ru.sberbank.edu;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomDigitComparatorTest {
    private List<Integer> arrTest;

    @BeforeEach
    public void setUp() {
        arrTest = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrTest.add(i);
        }
    }

    @Test
    public void testCompare() {
        CustomDigitComparator comparator = new CustomDigitComparator();

        Assert.assertEquals(0, comparator.compare(2, 2));
        Assert.assertEquals(0, comparator.compare(3, 3));
        Assert.assertEquals(0, comparator.compare(0, 0));
        Assert.assertEquals(1, comparator.compare(1, 2));
        Assert.assertEquals(1, comparator.compare(3, 4));
        Assert.assertEquals(1, comparator.compare(5, 6));
        Assert.assertEquals(1, comparator.compare(7, 8));
        Assert.assertEquals(1, comparator.compare(9, 10));
    }
}
