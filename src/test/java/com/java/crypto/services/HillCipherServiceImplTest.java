/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.crypto.services;

import com.java.crypto.domain.Result;
import com.java.crypto.jni.HillJni;
import com.java.crypto.repositories.WordRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author user
 */
public class HillCipherServiceImplTest {
    @Autowired
    private WordRepository wordRepository;
    
    public HillCipherServiceImplTest() {
    }

    /**
     * Test of cipher method, of class HillCipherServiceImpl.
     */
    @Test
    public void testCipher() throws Exception {
        System.out.println("cipher");
        String openText = "utorak";
        /*
        int[][] key = {
            {5, 8, 22},
            {2, 5, 24},
            {10, 20, 17}
        };
        */
        String key = "FIWCFYKUR";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        
        Result expResult = new Result("ODMTOC");
        Result result = instance.cipher(openText, key, alphabet);
        assertEquals(expResult, result);
        
        //second example
        String openText2 = "encryption";
        Result expResult2 = new Result("MRWJUVZYQJ");
        result = instance.cipher(openText2, key, alphabet);
        assertEquals(expResult2, result);
    }

    /**
     * Test of decipher method, of class HillCipherServiceImpl.
     */
    @Test
    public void testDecipher() throws Exception {
        System.out.println("decipher");
        String cipher = "poh";
        String key = "GYBNQKURP";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        
        Result expResult = new Result("ACT");
        Result result = instance.decipher(cipher, key, alphabet);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeWhiteSpaces method, of class HillCipherServiceImpl.
     */
    @Test
    public void testRemoveWhiteSpaces() {
        System.out.println("removeWhiteSpaces");
        String text = "ABC DE   F";
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        String expResult = "ABCDEF";
        String result = instance.removeWhiteSpaces(text);
        assertEquals(expResult, result);
    }


    /**
     * Test of modularInverse method, of class HillCipherServiceImpl.
     */
    @Test
    public void testModularInverse() {
        System.out.println("modularInverse");
        int number = 5;
        int modulo = 26;
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        int expResult = 21;
        int result = instance.modularInverse(number, modulo);
        assertEquals(expResult, result);
        
        //Second test
        number = 9;
        expResult = 3;
        result = instance.modularInverse(number, modulo);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of inverse method, of class HillCipherServiceImpl.
     */
    @Test
    public void testInverse() throws Exception {
        System.out.println("inverse");
        int[][] input = {
            {5,7,9},
            {4,3,8},
            {7,5,6}
        };
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        int[][] expResult = {
            {4,3,3},
            {6,19,22},
            {25,24,13}
        };
        int[][] result = instance.inverse(input, 26);
        assertArrayEquals(expResult, result);
        
        //second test
        int[][] input2 = {
            {6,24,1},
            {13,16,10},
            {20,17,15}
        };
        int[][] expResult2 = {
            {8,5,10},
            {21,8,21},
            {21,12,8}
        };
        result = instance.inverse(input2, 26);
        assertArrayEquals(expResult2, result);
        
        //third test
        int[][] input3 = {
            {1,5},
            {3,4}
        };
        int[][] expResult3 = {
            {2,17},
            {5,7}
        };
        result = instance.inverse(input3, 26);
        assertArrayEquals(expResult3, result);
        
        //fourth test
        int[][] input4 = {
            {3,1},
            {2,4}
        };
        Exception ex = assertThrows(Exception.class, () -> instance.inverse(input4, 26));
        assertTrue(ex.getMessage().contains("not invertible"));
    }

    /**
     * Test of determinant method, of class HillCipherServiceImpl.
     */
    @Test
    public void testDeterminant() {
        System.out.println("determinant");
        int[][] input = {
            {5,7,9},
            {4,3,8},
            {7,5,6}
        };
        int n = 3;
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        int expResult = 105;
        int result = instance.determinant(input, n);
        assertEquals(expResult, result);
    }

    /**
     * Test of replaceNegatives method, of class HillCipherServiceImpl.
     */
    @Test
    public void testReplaceNegatives() {
        System.out.println("replaceNegatives");
        int[][] input = {
            {-5,-27,9},
            {-14,3,8},
            {7,5,-6}
        };
        int modulo = 26;
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        int[][] expResult = {
            {21,25,9},
            {12,3,8},
            {7,5,20}
        };
        int[][] result = instance.replaceNegatives(input, modulo);        
        assertArrayEquals(expResult, result);
    }

    
    /**
     * Test of adjoint method, of class HillCipherServiceImpl.
     */
    @Test
    public void testAdjoint() {
        System.out.println("adjoint");
        int[][] input = {
            {5,-2,2,7},
            {1,0,0,3},
            {-3,1,5,0},
            {3,-1,-9,4}
        };
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        instance.adjoint(input);
    }

    
    /**
     * Test of divideText method, of class HillCipherServiceImpl.
     */
    @Test
    public void testDivideText() {
        System.out.println("divideText");
        String openText = "UTORAK";
        int size = 3;
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        String[] expResult = {"UTO", "RAK"};
        String[] result = instance.divideText(openText, size);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of numericalEquivalent method, of class HillCipherServiceImpl.
     */
    @Test
    public void testNumericalEquivalent() {
        System.out.println("numericalEquivalent");
        String text = "UTO";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        int[] expResult = {20, 19, 14};
        int[] result = instance.numericalEquivalent(text, alphabet);
        assertArrayEquals(expResult, result);
    }

    
    /**
     * Test of stringEquivalent method, of class HillCipherServiceImpl.
     */
    @Test
    public void testStringEquivalent() {
        System.out.println("stringEquivalent");
        int[][] cipher = {
            {14, 19},
            {3, 14},
            {12, 2}
        };
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        
        String expResult = "ODMTOC";
        String result = instance.stringEquivalent(cipher, alphabet);
        assertEquals(expResult, result);
        
        //second test
        int[][] cipher2 = {
            {21, 8, 0},
            {14, 12, 21},
            {11, 9, 20}
        };
        String expResult2 = "VOLIMJAVU";
        result = instance.stringEquivalent(cipher2, alphabet);
        assertEquals(expResult2, result);
    }


    @Test
    void testNextMatrix() {
        System.out.println("nextMatrix");
        int[][] currentMatrix = {
            {1, 2},
            {5, 7}
        };
        int step = 1;
        int[][] expResult = {
            {1, 2},
            {5, 8}
        };
        
        WordServiceImpl ws = new WordServiceImpl(wordRepository);
        HillCipherServiceImpl instance = new HillCipherServiceImpl(ws);
        
        int[][] result = instance.nextMatrix(currentMatrix, step);
        assertArrayEquals(expResult, result);

    }


    /**
     * Test of checkKeys method, of class HillCipherServiceImpl.
     */
    @Test
    public void testCheckKeys() throws Exception {
        
    }


}
