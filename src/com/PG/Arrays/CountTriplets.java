package com.PG.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountTriplets {
    public int countTriplets(int[] arr) {
        if(arr == null || arr.length == 0) {
            return 0;
        }

        Map<Integer, List<Integer>> xorIndexmap = new HashMap<>();

        List<Integer> zeroIndex = new ArrayList<>();
        zeroIndex.add(-1);
        xorIndexmap.put(0, zeroIndex);

        int sum=0, tripletCount =0;
        for(int i=0; i<arr.length; i++) {
            sum = sum^arr[i];
            if(xorIndexmap.containsKey(sum)) {
                tripletCount += findTriplets(xorIndexmap, i, sum);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                xorIndexmap.put(sum, indexList);
            }
        }

        return tripletCount;
    }

    public int findTriplets(Map<Integer,List<Integer> > xorIndexmap, int k, int sum) {
        List<Integer> indexList = xorIndexmap.get(sum);

        int count=0;
        for(Integer s: indexList) {
            int start = s+1;
            count += k-start;
        }
        indexList.add(k);
        return count;
    }
}
