package com.PG.misc;


import java.util.*;

public class TraxcnLRUSolution {

    enum FOOD { FIBER, FAT, CARB, NONE};

    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        int numberOfDays = in.nextInt();
        int numberOfIngredients = in.nextInt();
        int numOfIn = numberOfIngredients;

        Map<Integer,String> foodIndexIdMap = new HashMap<>();
        String[] res = new String[numberOfDays];
        LRUCache cache = new LRUCache(numberOfDays);

        //food category queue
        Queue<Integer> fiber = new LinkedList<>();
        Queue<Integer> fat = new LinkedList<>();
        Queue<Integer> carb = new LinkedList<>();

        String[] ip = {"CARBRice","FATOil","FIBERSpinach","FATCheese","FATEgg","FIBERBeans"};

        int dayCount=0, inCount=0;
        int minReqd = (int)Math.ceil(0.6*numberOfIngredients);
        for (int i = 0; i < numberOfDays; i++) {
            String ingredientId = ip[i];//in.next();

            checkInType(ingredientId, i, fiber, fat, carb, cache);

            dayCount++;
            inCount++;
            if(inCount<numOfIn) {
                res[dayCount-1] = "-";
            } else {
                Solution.FOOD f = cookingPossible(fiber, fat, carb, minReqd, inCount, numOfIn);
                if( f == Solution.FOOD.NONE) {
                    res[dayCount-1] = "-";

                } else if( f== Solution.FOOD.FAT){
                    res[dayCount-1] = getNextCookingItem(carb, fiber, minReqd, numOfIn, fat, cache);
                    inCount = cache.size();

                } else if( f== Solution.FOOD.CARB) {
                    res[dayCount-1] = getNextCookingItem(fat, fiber, minReqd, numOfIn, carb, cache);
                    inCount = cache.size();

                } else if( f== Solution.FOOD.FIBER) {
                    res[dayCount-1] = getNextCookingItem(fat, carb, minReqd, numOfIn, fiber, cache);
                    inCount = cache.size();
                }
            }
        }

        System.out.println(String.join(" ", res));
    }

    public static void checkInType(String ingredientId, int i, Queue<Integer> fiber,
                                   Queue<Integer> fat,Queue<Integer> carb, LRUCache cache) {

        if(ingredientId.contains("FIBER")) {
            fiber.add(i);
            cache.putEntry(i, ingredientId, fiber);
        } else if(ingredientId.contains("FAT")) {
            fat.add(i);
            cache.putEntry(i, ingredientId, fat);
        } else {
            carb.add(i);
            cache.putEntry(i, ingredientId, carb);
        }
    }

    public static Solution.FOOD cookingPossible(Queue<Integer> fiber, Queue<Integer> fat, Queue<Integer> carb, int minReqd, int inCount, int numOfIn) {
        if(fiber.size() >= minReqd && inCount >= numOfIn) {
            return Solution.FOOD.FIBER;
        } else if(fat.size() >= minReqd && inCount >= numOfIn) {
            return Solution.FOOD.FAT;
        } else if(carb.size() >= minReqd && inCount >= numOfIn) {
            return Solution.FOOD.CARB;
        } else {
            return Solution.FOOD.NONE;
        }
    }

    private static String getNextCookingItem(Queue<Integer> q1, Queue<Integer> q2, int minReqd, int totalReqd, Queue<Integer> selected, LRUCache cache) {

        StringBuilder res = new StringBuilder();
        for(int i =0; i<minReqd; i++) {
            int index = selected.poll();
            CacheEntry ce = cache.getEntryAndRemove(index);
            if(i !=0)
                res.append(":");
            res.append(ce.value);
        }

        for(int i=0; i<totalReqd-minReqd; i++) {
            //get nextEntry from cache
            //remove corresponding index from queue
            //append to result
            //no need for null check here bcoz we call
            // this function only when inCount >= numOfIn
            CacheEntry ce = cache.getEndAndRemove();
            int index = ce.foodTypeBucket.poll();
            res.append(":");
            res.append(ce.value);
        }

        return res.toString();
    }
}

class CacheEntry{
    //index
    public int key;
    public String value; //ingredientId
    public Queue<Integer> foodTypeBucket;
    CacheEntry right;
    CacheEntry left;

    public CacheEntry(int key, String value, Queue<Integer> foodTypeBucket) {
        this.key = key;
        this.value = value;
        this.foodTypeBucket = foodTypeBucket;
        this.right = null;
        this.left = null;
    }
}

 class LRUCache {
    Map<Integer, CacheEntry> lruHashMap;
    CacheEntry start, end;
    final int LRU_SIZE;

    public LRUCache() {
        LRU_SIZE = 4;
        this.start = null;
        this.end = null;
        this.lruHashMap = new HashMap<>();
    }

    public LRUCache(int size) {
        this.LRU_SIZE = size;
        this.start = null;
        this.end = null;
        this.lruHashMap = new HashMap<>();
    }

    public void putEntry(int key, String value, Queue<Integer> q) {
        if(lruHashMap.containsKey(key)) {
            CacheEntry e = lruHashMap.get(key);
            e.value = value;
            removeNode(e);
            addAtTop(e);
        } else {
            CacheEntry ce = new CacheEntry(key, value, q);

            if(lruHashMap.size() >= this.LRU_SIZE) {
                lruHashMap.remove(end.key);
                removeNode(end);
            }
            addAtTop(ce);
            lruHashMap.put(ce.key, ce);
        }
    }

    public CacheEntry getEntry(int key) {
        if(lruHashMap.containsKey(key)){
            CacheEntry ce = lruHashMap.get(key);
            removeNode(ce);
            addAtTop(ce);
            return ce;
        }
        return null;
    }

     private void addAtTop(CacheEntry node) {
        node.right = start;
        node.left = null;
        if(start != null) {
            start.left = node;
        }
        start = node;
        if(end == null) {
            end = start;
        }
     }

     private void removeNode(CacheEntry node) {
        if(node.right != null) {
            node.right.left = node.left;
        } else {
            end = node.left;
        }

        if(node.left != null) {
            node.left.right  = node.right;
        } else {
            start = node.right;
        }
     }

     public CacheEntry getEntryAndRemove(int key) {
         if(lruHashMap.containsKey(key)){
             CacheEntry ce = lruHashMap.get(key);
             removeNode(ce);
             lruHashMap.remove(ce.key);
             return ce;
         }
         return null;
     }

     public CacheEntry getStartAndRemove() {
        CacheEntry ce = start;
        //start = start.right;
         removeNode(start);
        lruHashMap.remove(ce.key);
        return ce;
     }

     public CacheEntry getEndAndRemove() {
        CacheEntry ce = end;
//        if(start == end) {
//            start = null;
//        }
//        end = end.left;

         removeNode(end);
        lruHashMap.remove(ce.key);
        return ce;
     }

     public int size(){
        return lruHashMap.size();
     }
 }