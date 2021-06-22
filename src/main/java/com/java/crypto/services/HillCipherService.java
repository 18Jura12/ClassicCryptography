package com.java.crypto.services;

import com.java.crypto.domain.Result;

import java.util.List;

public interface HillCipherService {
    Result cipher(String openText, Integer[][] key, String alphabet);
    Result decipher(String cipher, Integer[][] key, String alphabet);
    List<Result> decipherWithoutKey(String cipher);
}
