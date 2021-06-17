package com.java.crypto.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CaesarCipherServiceImpl implements CaesarCipherService {

    @Override
    public String cipher(String openText, Integer shift, String alphabet) {
        openText = openText.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < openText.length(); ++i) {
            if(Character.isWhitespace(openText.charAt(i))) {
                sb.append(openText.charAt(i));
            } else {
                sb.append(alphabet.charAt((alphabet.indexOf(openText.charAt(i)) + shift) % alphabet.length()));
            }
            
        }
        return sb.toString();
    }

    @Override
    public String decipher(String cipher, Integer shift, String alphabet) {
        cipher = cipher.toUpperCase();
        alphabet = alphabet.toUpperCase();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < cipher.length(); ++i) {
            if(Character.isWhitespace(cipher.charAt(i))) {
                sb.append(cipher.charAt(i));
            } else {
                
            }
        }
        
        return null;
    }
}
