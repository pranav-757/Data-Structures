package com.PG.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MaxspprodIB {
    //public static final int mod = 1000000007;

    public int maxSpecialProduct(List<Integer> A) {
        if(A == null || A.size() <=2)
            return 0;

        int len = A.size();
        int[] leftArr = new int[len+1];
        int[] rightArr = new int[len+1];

        Arrays.fill(leftArr, 0);
        Arrays.fill(rightArr, 0);

        int maxProd = Integer.MIN_VALUE;
        int currLeftSpecial;
        int currRightSpecial;
        Stack<Integer> rightSpecial = new Stack<Integer>();
        Stack<Integer> leftSpecial = new Stack<Integer>();

        int idx;

        for(idx = 0; idx<len; idx++) {

            while(!leftSpecial.isEmpty() && A.get(leftSpecial.peek() ) < A.get(idx) ) {
                leftSpecial.pop();
            }
            if(!leftSpecial.isEmpty()) {
                leftArr[idx] = leftSpecial.peek();
                System.out.println("setting left sp. for idx: "+idx+ " "+ leftSpecial.peek());
            }
            leftSpecial.push(idx);
        }


        for(idx = len-1; idx>=0; idx--) {
            if(idx == len-1) {
                currRightSpecial = A.get(idx);
                continue;
            }

            while(!rightSpecial.isEmpty() && A.get(rightSpecial.peek() ) < A.get(idx) ) {
                rightSpecial.pop();
            }

            if(!rightSpecial.isEmpty() ) {
                rightArr[idx] = rightSpecial.peek();
                System.out.println("setting right sp. for idx: "+idx+ " "+ rightSpecial.peek());
            }
            rightSpecial.push(idx );

        }

        for(idx = 0; idx<len; idx++) {
            if(leftArr[idx] !=0 && rightArr[idx] != 0) {
                maxProd = Math.max(maxProd, leftArr[idx]*rightArr[idx] );
                System.out.println("setting sp. prod for idx: "+ idx + " " +maxProd);
            }
        }

        return Math.max(maxProd, 0);
    }
}

