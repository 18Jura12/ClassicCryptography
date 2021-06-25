package com.java.crypto.services;

import com.java.crypto.domain.ExtendedResult;
import java.io.IOException;

public interface PlayfairCipherService {
    ExtendedResult cipher(String openText, String key, String language) throws IOException;
    ExtendedResult decipher(String cipher, String key, String language) throws IOException;
}
