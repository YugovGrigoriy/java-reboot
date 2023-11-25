package ru.sberbank.edu;

import java.util.Comparator;

public class CustomDigitComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer t1, Integer t2) {
        if ((t1 == null) || (t2 == null))
            throw new NullPointerException("На вход подаются числа, отличные от null")
                    ;
        if (t1 == t2) {
            return 0;
        }
        if (t1 % 2 == 0 && t2 % 2 == 1) {
            return -1;
        } else if (t1 % 2 == 1 && t2 % 2 == 0) {
            return 1;
        } else {
            return t1 - t2;
        }


    }
}
