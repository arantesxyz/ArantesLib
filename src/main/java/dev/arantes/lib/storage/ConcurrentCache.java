package dev.arantes.lib.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class ConcurrentCache<K, T> implements Cache<K, T> {
    private Map<K, T> cache = new ConcurrentHashMap<>();

    @Override
    public void put(K key, T object) {
        cache.put(key, object);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public T get(K key) {
        return cache.get(key);
    }

    @Override
    public void forEach(Consumer<T> consumer) {
        cache.values().forEach(consumer);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
}
