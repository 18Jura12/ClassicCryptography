package com.java.crypto.services;

import com.java.crypto.domain.Result;
import java.io.IOException;

import java.util.List;

public interface HillCipherService {
    Result cipher(String openText, String key, String alphabet) throws IOException;
    Result decipher(String cipher, String key, String alphabet) throws Exception;
    List<Result> decipherWithoutKey(String cipher) throws Exception;
}
