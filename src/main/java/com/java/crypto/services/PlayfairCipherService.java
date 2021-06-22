package com.java.crypto.services;

public interface PlayfairCipherService {
    String cipher(String openText, String key, String language);
    String decipher(String cipher, String key, String language);
}
