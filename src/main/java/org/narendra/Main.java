package org.narendra;

import org.narendra.hashing.ConsistentHashing;
import org.narendra.hashing.MapBasedConsistentHashing;

import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        ConsistentHashing ch = new MapBasedConsistentHashing(3);
        ch.addServer("server1");
        ch.addServer("server2");
        ch.addServer("server3");


        System.out.println("key1: is present on server: " + ch.getServer("key1"));
        System.out.println("key67890: is present on server: " + ch.getServer("key67890"));

        ch.removeServer("server1");
        System.out.println("After removing server1");

        System.out.println("key1: is present on server: " + ch.getServer("key1"));
        System.out.println("key67890: is present on server: " + ch.getServer("key67890"));
    }
}
