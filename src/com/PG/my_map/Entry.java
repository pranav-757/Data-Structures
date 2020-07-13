package com.PG.my_map;

public class Entry<K, V> {
    private final K key;
    private V val;
    private Entry<K,V> next;

    public Entry(K key, V val) {
        this.key = key;
        this.setVal(val);
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}
