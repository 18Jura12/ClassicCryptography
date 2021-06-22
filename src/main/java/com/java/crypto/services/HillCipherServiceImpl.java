package com.java.crypto.services;

import com.java.crypto.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HillCipherServiceImpl implements HillCipherService {

    private final WordService wordService;

    public HillCipherServiceImpl(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public Result cipher(String openText, Integer[][] key, String alphabet) {
        return null;
    }

    @Override
    public Result decipher(String cipher, Integer[][] key, String alphabet) {
        return null;
    }

    @Override
    public List<Result> decipherWithoutKey(String cipher) {
        return null;
    }
}
