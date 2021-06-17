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
    public void testCipher() {
        System.out.println("cipher");
        String openText = "ABC  DEF";
        Integer shift = 3;
        //String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabet = "ABCŽGHDEF";
        CaesarCipherServiceImpl instance = new CaesarCipherServiceImpl();
        String expResult = "ŽGH  ABC";
        String result = instance.cipher(openText, shift, alphabet);
        assertEquals(expResult, result);
    }

    /**
     * Test of decipher method, of class CaesarCipherServiceImpl.
     */
    /*
    @Test
    public void testDecipher() {
        System.out.println("decipher");
        String cipher = "";
        String alphabet = "";
        CaesarCipherServiceImpl instance = new CaesarCipherServiceImpl();
        String expResult = "";
        String result = instance.decipher(cipher, alphabet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    
}
