package com.java.crypto.services;

import com.java.crypto.domain.Result;

import java.io.IOException;

public interface CaesarCipherService {
    Result cipher(String openText, Integer shift, String alphabet) throws IOException;
    Result decipher(String cipher, Integer shift, String alphabet) throws IOException;
}
