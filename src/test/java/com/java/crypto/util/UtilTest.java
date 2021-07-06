/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.crypto.util;

import com.java.crypto.services.CaesarCipherServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class UtilTest {
    
    public UtilTest() {
    }

    /**
     * Test of validateInput method, of class Util.
     */
    @Test
    public void testValidateInput() {
        System.out.println("validateInput");
        String input = "ANA KARENJINA";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        boolean expResult = true;
        boolean result = Util.validateInput(input, alphabet);
        assertEquals(expResult, result);
        
        //Second example
        input = "AŽSTVA";
        expResult = false;
        result = Util.validateInput(input, alphabet);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validateAlphabet method, of class HillCipherServiceImpl.
     */
    @Test
    public void testValidateAlphabet() {
        System.out.println("validateAlphabet");
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";        
        boolean expResult = true;
        
        boolean result = Util.validateAlphabet(alphabet);
        assertEquals(expResult, result);
        
        //seconda example
        alphabet = "ABACDEF";
        expResult = false;
        result = Util.validateAlphabet(alphabet);
        assertEquals(expResult, result);
    }

    
}
