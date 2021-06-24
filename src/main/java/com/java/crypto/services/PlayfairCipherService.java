package com.java.crypto.services;

import com.java.crypto.domain.Result;
import java.io.IOException;

public interface PlayfairCipherService {
    Result cipher(String openText, String key, String language) throws IOException;
    Result decipher(String cipher, String key, String language) throws IOException;
}
