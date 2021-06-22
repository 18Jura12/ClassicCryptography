package com.java.crypto.services;

import com.java.crypto.domain.Result;

public interface PlayfairCipherService {
    Result cipher(String openText, String key, String language);
    Result decipher(String cipher, String key, String language);
}
