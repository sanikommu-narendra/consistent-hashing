package org.narendra.hashing;

public interface ConsistentHashing {
    void addServer(String server);
    void removeServer(String server);
    String getServer(String key);
}
