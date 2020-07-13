package com.PG.Arrays;

import java.util.*;

class contest1 {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if(arr.length <=1)
            return arr.length;

        int len = arr.length;
        Map<Integer, Integer> numCount = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<len; i++) {
            if(numCount.containsKey(arr[i]) ) {
                int count = numCount.get(arr[i]);
                numCount.put(arr[i], count+1);
            } else {
                numCount.put(arr[i],1);
            }
        }

        for(Map.Entry<Integer, Integer> entry: numCount.entrySet()) {
            pq.add(entry.getValue());
        }

//        int size = pq.size();
//        Iterator<Integer> it = pq.iterator();
//        while(it.hasNext() ) {
//            int val = it.next();
//            if(k>0 && k>= val) {
//                k -= val;
//                size--;
//            } else {
//                break;
//            }
//        }

        while(k>=pq.peek()) {
            int val = pq.poll();
            k -= val;
        }

        return pq.size();
    }
}
