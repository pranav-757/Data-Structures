package com.PG.my_map;

public class MyHashMap<K, V> {
    private static final int DEFAULT_SIZE=10;

    private Entry<K,V>[] buckets;

    public MyHashMap() {
        buckets = new Entry[DEFAULT_SIZE];
    }

    public MyHashMap(int capacity){
        buckets = new Entry[capacity];
    }

    public V get(K key) {

    }
}
