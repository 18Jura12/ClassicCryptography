package com.java.crypto.services;

import java.util.List;

public interface HillCipherService {
    String cipher(String openText, Integer[][] key, String alphabet);
    String decipher(String cipher, Integer[][] key, String alphabet);
    List<String> decipherWithoutKey(String cipher);
}
