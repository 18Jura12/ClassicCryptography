/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.crypto.util;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Ines
 */
@Slf4j
public class Util {
    public static boolean validateInput(String input, String alphabet) {
        //log.info("Validate input.");
        for(int i = 0; i < input.length(); ++i) {
            if(!alphabet.contains(Character.toString(input.charAt(i))) && !Character.isWhitespace(input.charAt(i))) {
                //log.info("Input incorrect.");
                return false;
            }
        }
        
        //log.info("Input correct.");
        return true;
    }
    
}
