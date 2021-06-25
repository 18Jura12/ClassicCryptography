/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.crypto.services;

import com.java.crypto.domain.Result;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author user
 */
public class HillCipherServiceImplTest {
    
    public HillCipherServiceImplTest() {
    }

    /**
     * Test of cipher method, of class HillCipherServiceImpl.
     */
    @Test
    public void testCipher() throws Exception {
        System.out.println("cipher");
        String openText = "";
        double[][] key = null;
        String alphabet = "";
        HillCipherServiceImpl instance = null;
        Result expResult = null;
        Result result = instance.cipher(openText, key, alphabet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decipher method, of class HillCipherServiceImpl.
     */
    /*
    @Test
    public void testDecipher() {
        System.out.println("decipher");
        String cipher = "";
        double[][] key = null;
        String alphabet = "";
        HillCipherServiceImpl instance = null;
        Result expResult = null;
        Result result = instance.decipher(cipher, key, alphabet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decipherWithoutKey method, of class HillCipherServiceImpl.
     */
    /*
    @Test
    public void testDecipherWithoutKey() {
        System.out.println("decipherWithoutKey");
        String cipher = "";
        HillCipherServiceImpl instance = null;
        List<Result> expResult = null;
        List<Result> result = instance.decipherWithoutKey(cipher);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeWhiteSpaces method, of class HillCipherServiceImpl.
     */
    @Test
    public void testRemoveWhiteSpaces() {
        System.out.println("removeWhiteSpaces");
        String text = "ABC DE   F";
        HillCipherServiceImpl instance = null;
        String expResult = "ABCDEF";
        String result = instance.removeWhiteSpaces(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of inverse method, of class HillCipherServiceImpl.
     */
    @Test
    public void testInverse() {
        System.out.println("inverse");
        double[][] input = new double[3][3];
        HillCipherServiceImpl instance = null;
        double[][] expResult = null;
        double[][] result = instance.inverse(input);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
