package com.PG.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class Pascal {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        ArrayList<ArrayList<Integer>> ans = s.solve(4);
        System.out.println(ans.toString());
    }
}

class Solution1 {
    public ArrayList<ArrayList<Integer>> solve(int A) {
        if(A<=0){
            return new ArrayList<ArrayList<Integer>>();
        }

        int[][] sumHelper = new int[2][A];

        for(int i=0; i<2; i++) {
            for(int j=0; j<A; j++) {
                sumHelper[i][j] =0;
            }
        }

        int index=0;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for(int i=0; i<A; i++) {
            ArrayList<Integer> row = new ArrayList<>();

            if(i==0) {
                row.add(1);
                sumHelper[0][0] =1;
                res.add(row);
                continue;
            }
            for(int j=0; j<=i; j++) {
                index = i&1;
                if(j == 0 || j == i)
                    row.add(1);
                int ele = sumHelper[1-index][j] + sumHelper[1-index][j-1];
                row.add(ele);
            }
            res.add(row);
        }
        return res;
    }
}