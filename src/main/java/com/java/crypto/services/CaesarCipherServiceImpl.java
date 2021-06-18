package com.java.crypto.services;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CaesarCipherServiceImpl implements CaesarCipherService {

    @Override
    public String cipher(String openText, Integer shift, String alphabet) throws IOException {
        openText = openText.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        if(!validateInput(openText, alphabet)) {
            throw new IOException("Characters in input do not match the alphabet.");
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
        
        return sb.toString();
    }

    @Override
    public String decipher(String cipher, Integer shift, String alphabet) throws IOException{
        cipher = cipher.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        if(!validateInput(cipher, alphabet)) {
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
        
        return sb.toString();
    }
    
    public boolean validateInput(String input, String alphabet) {
        log.info("Validate input.");
        for(int i = 0; i < input.length(); ++i) {
            if(!alphabet.contains(Character.toString(input.charAt(i))) && !Character.isWhitespace(input.charAt(i))) {
                log.info("Input incorrect.");
                return false;
            }
        }
        
        log.info("Input correct.");
        return true;
    }
}
