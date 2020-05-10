package dev.arantes.spigot.tempmute.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache<K, T> {
    private Map<K, T> cache = new ConcurrentHashMap<>();

    public void put(K key, T object) {
        cache.put(key, object);
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public T get(K key) {
        return cache.get(key);
    }

    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
}