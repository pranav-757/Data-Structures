package com.PG.Stack;

import java.util.Arrays;
import java.util.List;

public class StackMain {

    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(new Integer[]{5, 9, 6, 8, 6, 4, 6, 9, 5, 4, 9} );
        MaxspprodIB maxspprodIB = new MaxspprodIB();
        maxspprodIB.maxSpecialProduct(A);
    }
}
