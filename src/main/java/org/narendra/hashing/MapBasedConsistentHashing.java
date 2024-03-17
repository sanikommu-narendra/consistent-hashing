package org.narendra.hashing;

import org.narendra.hashing.ConsistentHashing;
import org.narendra.hashing.Hasher;

import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapBasedConsistentHashing implements ConsistentHashing {

    private final TreeMap<Long, String> ring;
    private final int numberOfReplicas;
    private final Hasher hasher;

    private static final int DEFAULT_NUMBER_OF_REPLICAS = 5;

    public MapBasedConsistentHashing(int numberOfReplicas) throws NoSuchAlgorithmException {
        this.ring = new TreeMap<>();
        this.numberOfReplicas = numberOfReplicas;
        this.hasher = new Hasher("MD5");
    }

    public MapBasedConsistentHashing() throws NoSuchAlgorithmException {
        this(DEFAULT_NUMBER_OF_REPLICAS);
    }

    public static void main(String[] args) {
    }

    @Override
    public void addServer(String server) {
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hasher.generateHash(server + i);
            ring.put(hash, server);
        }
    }

    @Override
    public void removeServer(String server) {
        for (int i = 0; i < numberOfReplicas; i++) {
            long hash = hasher.generateHash(server + i);
            ring.remove(hash);
        }
    }

    @Override
    public String getServer(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        long hash = hasher.generateHash(key);
        if (!ring.containsKey(hash)) {
            SortedMap<Long, String> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(hash);
    }
}
