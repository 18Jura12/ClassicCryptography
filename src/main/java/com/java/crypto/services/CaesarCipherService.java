package com.java.crypto.services;

public interface CaesarCipherService {
    String cipher(String openText, Integer shift, String alphabet);
    String decipher(String cipher, String alphabet);
}
