package org.narendra.hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private final MessageDigest messageDigest;

    Hasher(String algorithm) throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance(algorithm);
    }
    public long generateHash(String key) {
        messageDigest.reset();
        messageDigest.update(key.getBytes());
        byte[] digest = messageDigest.digest();
        return ((long) (digest[3] & 0xFF) << 24) |
                ((long) (digest[2] & 0xFF) << 16) |
                ((long) (digest[1] & 0xFF) << 8) |
                ((long) (digest[0] & 0xFF));
    }

}
