//package com.handsome.frame.android.test;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * Created with Andrid Studio.
// * User:shuaizhimin
// * Date:16/4/11
// * Time:下午6:32
// */
//public class LruCache<K, V> {
//    private final LinkedHashMap<K, V> map;
//
//    /** Size of this cache in units. Not necessarily the number of elements. */
//    private int size;
//    private int maxSize;
//
//    private int putCount;
//    private int createCount;
//    private int evictionCount;
//    private int hitCount;
//    private int missCount;
//    public LruCache(int maxSize) {
//        if (maxSize <= 0) {
//            throw new IllegalArgumentException("maxSize <= 0");
//        }
//        this.maxSize = maxSize;
//        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
//    }
//
//    public final V put(K key,V value){
//        if (key == null || value == null) {
//            throw new NullPointerException("key == null || value == null");
//        }
//
//        V previous;
//        synchronized (this) {
//            putCount++;
//            size += safeSizeOf(key, value);
//            previous = map.put(key, value);
//            if (previous != null) {
//                size -= safeSizeOf(key, previous);
//            }
//        }
//
//        if (previous != null) {
//            entryRemoved(false, key, previous, value);
//        }
//
//        trimToSize(maxSize);
//        return previous;
//    }
//    public void trimToSize(int maxSize) {
//        while (true) {
//            K key;
//            V value;
//            synchronized (this) {
//                if (size < 0 || (map.isEmpty() && size != 0)) {
//                    throw new IllegalStateException(getClass().getName()
//                            + ".sizeOf() is reporting inconsistent results!");
//                }
//
//                if (size <= maxSize) {
//                    break;
//                }
//
//                Map.Entry<K, V> toEvict = map.eldest();
//                if (toEvict == null) {
//                    break;
//                }
//
//                key = toEvict.getKey();
//                value = toEvict.getValue();
//                map.remove(key);
//                size -= safeSizeOf(key, value);
//                evictionCount++;
//            }
//
//            entryRemoved(true, key, value, null);
//        }
//    }
//
//
//    private int safeSizeOf(K key, V value) {
//        int result = sizeOf(key, value);
//        if (result < 0) {
//            throw new IllegalStateException("Negative size: " + key + "=" + value);
//        }
//        return result;
//    }
//    protected int sizeOf(K key, V value) {
//        return 1;
//    }
//}
