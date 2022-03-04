package concurrency;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class BlockingMap<K, V> {

    private Map<K, ArrayBlockingQueue<V>> map = new ConcurrentHashMap<>();

    private BlockingQueue<V> getQueue(K key) {
        return map.compute(key, (k, v) -> v == null ? new ArrayBlockingQueue<>(1) : v);
    }

    public void put(K key, V value) {
        getQueue(key).add(value);
    }

    public V get(K key) throws InterruptedException {
        return getQueue(key).take();
    }

}