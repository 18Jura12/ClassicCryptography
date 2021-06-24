package com.java.crypto.services;

import com.java.crypto.domain.Result;
import com.java.crypto.util.Util;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HillCipherServiceImpl implements HillCipherService {

    private final WordService wordService;
    private static final char DUMMY_CHAR = 'X';

    public HillCipherServiceImpl(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public Result cipher(String openText, Integer[][] key, String alphabet) throws IOException{
        openText = openText.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        if(!Util.validateInput(openText, alphabet)) {
            throw new IOException("Characters in input do not match characters in alphabet.");
        }
        
        openText = removeWhiteSpaces(openText);
        
        while(openText.length() % key.length != 0) {
            openText += DUMMY_CHAR;
        }
        
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
    
    /*
    Removes white spaces in the given openText.
    */
    public String removeWhiteSpaces(String text) {
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < text.length(); ++i) {
            if(!Character.isWhitespace(text.charAt(i))) {
                builder.append(text.charAt(i));
            }
        }
        return builder.toString();
    }
}
