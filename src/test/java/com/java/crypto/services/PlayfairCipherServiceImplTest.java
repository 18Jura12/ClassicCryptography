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
 * @author Ines
 */
public class PlayfairCipherServiceImplTest {
    
    public PlayfairCipherServiceImplTest() {
    }

    /**
     * Test of cipher method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testCipher() {
        System.out.println("cipher");
        String openText = "CRYPTOGRAPHY";
        String key = "PLAYFAIR";
        String language = "ENGLISH";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String expResult = "DBFLNQOGYLKA";
        String result = instance.cipher(openText, key, language);
        assertEquals(expResult, result);
    }

    /**
     * Test of decipher method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testDecipher() {
        System.out.println("decipher");
        String cipher = "DBFLNQOGYLKA";
        String key = "PLAYFAIR";
        String language = "ENGLISH";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String expResult = "CRYPTOGRAPHY";
        String result = instance.decipher(cipher, key, language);
        assertEquals(expResult, result);
        
        cipher = "CKFLETIJKSVIXGIEQOSAGAPLTEAUKHCAETAFCKTO";
        key = "TAJNOPIS";
        language = "CROATIAN";
        expResult = "PREMDASAMPLAYFAIRNIJENIKADTVRDIODAJEPRON";
        result = instance.decipher(cipher, key, language);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeWhiteSpaces method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testRemoveWhiteSpaces() {
        System.out.println("removeWhiteSpaces");
        String text = "AB CD  X M";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String expResult = "ABCDXM";
        String result = instance.removeWhiteSpaces(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of processDummyChar method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testProcessDummyChar() {
        System.out.println("processDummyChar");
        String text = "HELLO";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String expResult = "HELXLXOX";
        String result = instance.processDummyChar(text);
        assertEquals(expResult, result);
        
        text = "HELL";
        expResult = "HELXLX";
        result = instance.processDummyChar(text);
        assertEquals(expResult, result);
    }

    /**
     * Test of formatText method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testFormatText() {
        System.out.println("formatText");
        String text = "DIJAGRAM";
        String language = "ENGLISH";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String expResult = "DIIAGRAM";
        String result = instance.formatText(text, language);
        assertEquals(expResult, result);
        
        text = "WAVE";
        language = "CROATIAN";
        expResult = "VAVE";
        result = instance.formatText(text, language);
        assertEquals(expResult, result);
    }

    /**
     * Test of prepareKey method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testPrepareKey() {
        System.out.println("prepareKey");
        String key = "PLAYFAIR";
        String language = "ENGLISH";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String expResult = "PLAYFIR";
        String result = instance.prepareKey(key, language);
        assertEquals(expResult, result);
    }

    /**
     * Test of createMatrix method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testCreateMatrix() {
        System.out.println("createMatrix");
        String key = "PLAYFIR";
        String language = "ENGLISH";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        instance.createMatrix(key, language);
        String output = "PLAYFIRBCDEGHKMNOQSTUVWXZ";
        for(int i = 0, k = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                assertEquals(output.charAt(k++), instance.matrix[i][j]);
            }
        }
    }


    /**
     * Test of makeBlocks method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testMakeBlocks() {
        System.out.println("makeBlocks");
        String text = "DECODEHELXLXOX";
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        String[] expResult = new String[] {"DE", "CO", "DE", "HE", "LX", "LX", "OX"};
        String[] result = instance.makeBlocks(text);
        assertArrayEquals(expResult, result);
    }


    /**
     * Test of getPosition method, of class PlayfairCipherServiceImpl.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        char letter = 'S';
        PlayfairCipherServiceImpl instance = new PlayfairCipherServiceImpl();
        int[] expResult = new int[]{3, 3};
        
        instance.createMatrix("PLAYFIR", "ENGLISH");
        
        int[] result = instance.getPosition(letter);
        assertArrayEquals(expResult, result);
    }

}
