package dev.arantes.lib.storage;

import java.util.function.Consumer;

public interface Cache<K, T> {
    void put(K key, T object);
    void remove(K key);
    T get(K key);
    void forEach(Consumer<T> consumer);
    boolean containsKey(K key);
}
