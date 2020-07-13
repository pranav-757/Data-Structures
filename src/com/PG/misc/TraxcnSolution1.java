package com.PG.misc;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * to be solved using LRU cache
 */
class Solution {

    enum FOOD { FIBER, FAT, CARB, NONE};

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int numberOfDays = in.nextInt();
        int numberOfIngredients = in.nextInt();

        int numOfIn = numberOfIngredients;

        Map<Integer,String> foodIndexIdMap = new HashMap<>();
        String[] res = new String[numberOfDays];
        Queue<Integer> fiber = new LinkedList<>();
        Queue<Integer> fat = new LinkedList<>();
        Queue<Integer> carb = new LinkedList<>();

        String[] ip = {"CARBRice","FATOil","FIBERSpinach","FATCheese","FATEgg","FIBERBeans"};

        int dayCount=0, inCount=0;
        int minReqd = (int)Math.ceil(0.6*numberOfIngredients);
        for (int i = 0; i < numberOfDays; i++) {
            String ingredientId = ip[i];//in.next();

            checkInType(ingredientId, i, fiber, fat, carb, foodIndexIdMap);

            dayCount++;
            inCount++;
            if(inCount<numOfIn) {
                res[dayCount-1] = "-";
            } else {
                FOOD f = cookingPossible(fiber, fat, carb, minReqd, inCount, numOfIn);
                if( f == FOOD.NONE) {
                    res[dayCount-1] = "-";
                } else if( f== FOOD.FAT){
                    res[dayCount-1] = getNextCookingItem(carb, fiber, minReqd, numOfIn, fat, foodIndexIdMap);
                    inCount = foodIndexIdMap.size();
                } else if( f== FOOD.CARB) {
                    res[dayCount-1] = getNextCookingItem(fat, fiber, minReqd, numOfIn, carb, foodIndexIdMap);
                    inCount = foodIndexIdMap.size();
                } else if( f== FOOD.FIBER) {
                    res[dayCount-1] = getNextCookingItem(fat, carb, minReqd, numOfIn, fiber, foodIndexIdMap);
                    inCount = foodIndexIdMap.size();
                }
            }
        }

        System.out.println(String.join("=", res));
    }

    private static String getNextCookingItem(Queue<Integer> q1, Queue<Integer> q2, int minReqd, int totalReqd, Queue<Integer> selected, Map<Integer, String> foodIndexIdMap) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(3);
        StringBuilder res = new StringBuilder();
        for(int i =0; i<minReqd; i++) {
            int index = selected.poll();
            pq.add(index);

        }

        for(int i=0; i<totalReqd-minReqd; i++) {
            int minIndex = q1.peek()<q2.peek()? q1.poll():q2.poll();
            pq.add(minIndex);
        }

        for(Integer i: pq) {
            res.append(foodIndexIdMap.get(i));
            res.append(":");
            foodIndexIdMap.remove(i);
        }
        return res.toString();
    }

    public static void checkInType(String ingredientId, int i, Queue<Integer> fiber,
                                   Queue<Integer> fat,Queue<Integer> carb, Map<Integer,String> foodType) {

        if(ingredientId.contains("FIBER")) {
            fiber.add(i);
            foodType.put(i, ingredientId);
        } else if(ingredientId.contains("FAT")) {
            fat.add(i);
            foodType.put(i, ingredientId);
        } else {
            carb.add(i);
            foodType.put(i, ingredientId);
        }
    }

    public static FOOD cookingPossible(Queue<Integer> fiber, Queue<Integer> fat, Queue<Integer> carb, int minReqd, int inCount, int numOfIn) {
        if(fiber.size() >= minReqd && inCount >= numOfIn) {
            return FOOD.FIBER;
        } else if(fat.size() >= minReqd && inCount >= numOfIn) {
            return FOOD.FAT;
        } else if(carb.size() >= minReqd && inCount >= numOfIn) {
            return FOOD.CARB;
        } else {
            return FOOD.NONE;
        }
    }
}