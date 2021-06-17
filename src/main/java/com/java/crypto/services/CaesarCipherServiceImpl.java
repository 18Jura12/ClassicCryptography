package com.java.crypto.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CaesarCipherServiceImpl implements CaesarCipherService {

    @Override
    public String cipher(String openText, Integer shift, String alphabet) {
        return null;
    }

    @Override
    public String decipher(String cipher, String alphabet) {
        return null;
    }
}
