package com.PG.Arrays;

public class SmallestNumForPattern {
    String pattern = "MNM";

    /**
     * M-> decending i.e. digit[i]>digit[i+1]
     * N-> ascending i.e. digit[i]<digit[i+1]
     * All digits are unique
     * pattern length is <=8
     */

    public String getSmallestNum() {

        if(this.pattern.length() > 8) {
            return null;
        }

        int patLen = pattern.length();
        char[] digits = new char[patLen+1];

        for(int i=0; i<=patLen; i++)
            digits[i] = (char)(i + '1');

        int startOrder = 0;
        int endOrder = 0;
        char currOrder = pattern.charAt(startOrder);

        while(endOrder < patLen) {
            while(endOrder< patLen && currOrder == pattern.charAt(endOrder)) {
                endOrder++;
            }

            digitSwap(startOrder, endOrder, digits);
            if(endOrder == patLen)
                break;
            startOrder = endOrder;
            currOrder = pattern.charAt(endOrder);

        }
        String res = new String(digits);

        System.out.println(""+res);
        return res;
    }

    private void digitSwap(int startOrder, int endOrder, char[] digits) {
        while(startOrder<endOrder) {
            Character temp = digits[startOrder];
            digits[startOrder] = digits[endOrder];
            digits[endOrder] = digits[startOrder];
            startOrder++;
            endOrder--;
        }
    }
}
