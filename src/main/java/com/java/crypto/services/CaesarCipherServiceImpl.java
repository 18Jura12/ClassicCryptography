package com.java.crypto.services;

import java.io.IOException;

import com.java.crypto.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.java.crypto.util.Util;

@Slf4j
@Service
public class CaesarCipherServiceImpl implements CaesarCipherService {

    @Override
    public Result cipher(String openText, Integer shift, String alphabet) throws IOException {
        openText = openText.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        if(!Util.validateInput(openText, alphabet)) {
            throw new IOException("Characters in input do not match characters in alphabet.");
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < openText.length(); ++i) {
            if(Character.isWhitespace(openText.charAt(i))) {
                sb.append(openText.charAt(i));
            } else {
                int result = alphabet.indexOf(openText.charAt(i)) + shift;
                int newIndex = (result < 0) ? (Math.floorMod(result, alphabet.length())) : (result % alphabet.length());
                sb.append(alphabet.charAt(newIndex));
            }
        }
        
        return new Result(sb.toString());
    }

    @Override
    public Result decipher(String cipher, Integer shift, String alphabet) throws IOException{
        cipher = cipher.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        if(!Util.validateInput(cipher, alphabet)) {
            throw new IOException("Characters in input do not match the alphabet.");
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cipher.length(); ++i) {
            if(Character.isWhitespace(cipher.charAt(i))) {
                sb.append(cipher.charAt(i));
            } else {
                int result = alphabet.indexOf(cipher.charAt(i)) - shift;
                int newIndex = (result < 0) ? (Math.floorMod(result, alphabet.length())) : (result % alphabet.length());
                sb.append(alphabet.charAt(newIndex));
            }
        }
        
        return new Result(sb.toString());
    }
}
