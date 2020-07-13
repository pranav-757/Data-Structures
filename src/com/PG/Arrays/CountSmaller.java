package com.PG.Arrays;

import java.util.ArrayList;
import java.util.List;

public class CountSmaller {
    public List<Integer> countSmaler(int[] a) {
        int len = a.length;
        Item[] items = new Item[len];
        int i=0;

        for(int e: a) {
            items[i] = new Item(e, i);
            i++;
        }

        int[] count = new int[len];

        mergeSort(items, 0, len-1, count);

        return  new ArrayList<Integer>();

    }


    class Item {
        int val;
        int index;

        public Item(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    private void mergeSort(Item[] items, int low, int high, int[] count) {

        if(low>=high) {
            return;
        }
        int mid = low + (high-low)/2;
        mergeSort(items, low, mid, count);
        mergeSort(items, mid+1, high, count);
        merge(items, low, mid, mid+1, high, count);
    }

    private void merge(Item[] items, int low, int lowEnd, int high, int highEnd, int[] count) {
        int len = highEnd - low +1;
        Item[] sorted = new Item[len];
        int counter =0, lowptr = low, highptr = high, index=0;

        while(lowptr <= lowEnd && highptr<=highEnd) {
            if(items[lowptr].val > items[highptr].val) {
                counter++;
                sorted[index++] = items[highptr++];
            } else {
                count[items[lowptr].index] += counter;
                sorted[index++] = items[lowptr++];
            }
        }

        while(lowptr<=lowEnd) {
            count[items[lowptr].index] += counter;
            sorted[index++] = items[lowptr++];
        }

        while (highptr<=highEnd)
            sorted[index++] = items[highptr++];
    }
}
