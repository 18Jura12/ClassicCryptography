package com.java.crypto.services;

import java.io.IOException;

public interface CaesarCipherService {
    String cipher(String openText, Integer shift, String alphabet) throws IOException;
    String decipher(String cipher, Integer shift, String alphabet) throws IOException;
}
