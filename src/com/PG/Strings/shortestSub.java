package com.PG.Strings;

import java.util.HashMap;
import java.util.Map;

public class shortestSub {
    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.minWindow("ADOBECODEBANC", "ABC") );
    }
}


class Solution {
    public String minWindow(String A, String B) {

        int len_pat = B.length(), len_str = A.length();
        int count = 0, start=0, start_index = -1, substr_len = Integer.MAX_VALUE;

        if(len_str<len_pat) {
            return "";
        }

        Map<Character, Integer> hash_pat = new HashMap<>();
        Map<Character, Integer> hash_str = new HashMap<>();

        for(int i=0; i<len_pat; i++) {
            char c = B.charAt(i);
            if(hash_pat.containsKey(c) ) {
                hash_pat.put(c, hash_pat.get(c)+1 );
            } else {
                hash_pat.put(c, 1);
            }
        }

        for(int i =0; i<len_str; i++) {
            char c = A.charAt(i);

            //this doesn't work
            //hash_str.put(c, hash_str.get(c)+1 );

            if(hash_str.containsKey(c) ) {
                hash_str.put(c, hash_str.get(c)+1 );
            } else {
                hash_str.put(c,1 );
            }

            if(hash_pat.containsKey(c) ) {
                if( hash_str.get(c) <= hash_pat.get(c) )
                    count++;
            } else {
                continue;
            }

            if(count == len_pat) {
                while( !hash_pat.containsKey(A.charAt(start) ) ||
                        hash_str.get(A.charAt(start) )> hash_pat.get(A.charAt(start) ) ) {
                    char c1 = A.charAt(start);

                    hash_str.put(c1, hash_str.get(c1)-1 );
                    start++;
                }
                int len = i-start+1;
                if(len<substr_len) {
                    substr_len = len;
                    start_index=start;
                }
            }
        }

        if(start_index == -1) {
            return "";
        }
        return A.substring(start_index, start_index+substr_len);
    }
}
