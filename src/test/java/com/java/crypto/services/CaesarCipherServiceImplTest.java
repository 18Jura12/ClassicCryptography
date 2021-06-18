/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.crypto.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class CaesarCipherServiceImplTest {
    
    public CaesarCipherServiceImplTest() {
    }

    /**
     * Test of cipher method, of class CaesarCipherServiceImpl.
     */
    @Test
    public void testCipher() throws Exception {
        System.out.println("cipher");
        String openText = "ABC  DEF";
        Integer shift = 3;
        String alphabet = "ABCŽGHDEF";
        
        CaesarCipherServiceImpl instance = new CaesarCipherServiceImpl();
        String expResult = "ŽGH  ABC";
        
        String result = instance.cipher(openText, shift, alphabet);
        assertEquals(expResult, result);
        
        
        //Second example
        openText = "DCODE CAESAR";
        shift = -3;
        expResult = "AZLAB ZXBPXO";
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        result = instance.cipher(openText, shift, alphabet);
        assertEquals(expResult, result);
    }

    /**
     * Test of decipher method, of class CaesarCipherServiceImpl.
     */
    @Test
    public void testDecipher() throws Exception{
        System.out.println("decipher");
        String cipher = "gFrgh Fdhvdu";
        Integer shift = 3;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        CaesarCipherServiceImpl instance = new CaesarCipherServiceImpl();
        String expResult = "DCODE CAESAR";
        
        String result = instance.decipher(cipher, shift, alphabet);
        assertEquals(expResult, result);
        
        
        //Second example
        cipher = "abc  def";
        shift = -2;
        expResult = "CDE  FGH";
        
        result = instance.decipher(cipher, shift, alphabet);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateInput method, of class CaesarCipherServiceImpl.
     */
    @Test
    public void testValidateInput() {
        System.out.println("validateInput");
        String input = "ANA KARENJINA";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        CaesarCipherServiceImpl instance = new CaesarCipherServiceImpl();
        boolean expResult = true;
        boolean result = instance.validateInput(input, alphabet);
        assertEquals(expResult, result);
        
        
        //Second example
        input = "AŽSTVA";
        expResult = false;
        result = instance.validateInput(input, alphabet);
        assertEquals(expResult, result);
    }
    
}
