package org.example.hashing;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.example.hashing.HashUtil.hashLong;

/**
 * Ring hashing using consistent hashing
 * @param <T>
 */
public class ConsistentHash<T> {
    /**
     * 1. Virtual nodes per server (~100–200 typical; tune)
     * 2. Weights by adding proportionally more virtual nodes for bigger servers.
     * 3. Replication: walk further around the ring to pick R distinct servers.
     *
     */
    private final SortedMap<Long,T> ring = new TreeMap<>();
    private final int vnodes;
    public ConsistentHash(int vnodes, Collection<T> nodes) {
        this.vnodes = vnodes;
        for (T n : nodes) add(n);
    }
    void add(T node) {
        for (int i=0; i<vnodes; i++) {
            long h = hashLong(node.toString() + "#" + i);
            ring.put(h, node);
        }
    }
    void remove(T node) {
        for (int i=0; i<vnodes; i++) {
            long h = hashLong(node.toString() + "#" + i);
            ring.remove(h);
        }
    }
    T get(String key) {
        long h = hashLong(key);
        SortedMap<Long,T> tail = ring.tailMap(h);
        Long k = tail.isEmpty() ? ring.firstKey() : tail.firstKey();
        return ring.get(k);
    }
}
