package com.PG.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HotelBookings {
    public boolean hotel(List<Integer> arrive, List<Integer> depart, int k) {

        if(k == 0)
            return false;
        if(k>= arrive.size() )
            return true;

        int len = arrive.size();
        int[][] visit = new int[arrive.size()][2];

        for(int i=0; i<len; i++) {
            visit[i][0] = arrive.get(i);
            visit[i][1] = depart.get(i);
        }

        Arrays.sort(visit, new D2Comparator() );

        int count = 1, maxCount = 1;
        int aptr = 1, dptr =0;

        while(aptr<len) {
            if(visit[aptr][0]<visit[dptr][1]) {
                count++;
                maxCount = Math.max(maxCount, count);
                aptr++;
            } else {
                count--;
                dptr++;
            }
        }

        return maxCount>k?false:true;
    }

    public class D2Comparator implements Comparator<int[]> {

        public int compare(int[] a, int[] b) {
            return a[0] - b[0];
        }
    }

    public static void main(String[] args) {
        List<Integer> arrive = Arrays.asList( 9, 47, 17, 39, 35, 35, 20, 18, 15, 34, 11, 2, 45, 46, 15, 33, 47, 47, 10, 11, 27);
        List<Integer> depart = Arrays.asList( 32, 82, 39, 86, 81, 58, 64, 53, 40, 76, 40, 46, 63, 88, 56, 52, 50, 72, 22, 19, 38);

        HotelBookings hb = new HotelBookings();
        hb.hotel(arrive, depart, 16);
    }
}

