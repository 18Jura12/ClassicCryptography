package com.java.crypto.services;

import com.java.crypto.domain.Result;
import com.java.crypto.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HillCipherServiceImpl implements HillCipherService {

    private final WordService wordService;
    private static final char DUMMY_CHAR = 'X';
    
    public int[][] adjMatrix;

    public HillCipherServiceImpl(WordService wordService) {
        this.wordService = wordService;
    }
    

    @Override
    public Result cipher(String openText, int[][] key, String alphabet) throws IOException{
        openText = openText.toUpperCase();
        alphabet = alphabet.toUpperCase();
        int counter = 0;
        
        if(!Util.validateInput(openText, alphabet)) {
            throw new IOException("Characters in input do not match characters in alphabet.");
        }
        
        openText = removeWhiteSpaces(openText);
        alphabet = removeWhiteSpaces(alphabet);
        
        key = replaceNegatives(key, alphabet.length());
        
        while(openText.length() % key.length != 0) {
            openText += DUMMY_CHAR;
            counter++;
        }
        log.info("counter: " + Integer.toString(counter));
        
        String[] blocks = divideText(openText, key.length);
        int[][] textMatrix = new int[key.length][blocks.length];
        
        for(int i = 0; i < blocks.length; ++i) {
            int[] temp = numericalEquivalent(blocks[i], alphabet);
            for(int j = 0; j < key.length; ++j) {
                textMatrix[j][i] = temp[j];
            }
        }
        
        textMatrix = multiply(textMatrix, key, alphabet.length());
        String result = stringEquivalent(textMatrix, alphabet);
        result = result.substring(0, result.length() - counter);
        
        return new Result(result);
    }

    @Override
    public Result decipher(String cipher, int[][] key, String alphabet) throws Exception{
        cipher = cipher.toUpperCase();
        alphabet = alphabet.toUpperCase();
        int counter = 0;
        
        if(!Util.validateInput(cipher, alphabet)) {
            throw new IOException("Characters in input do not match characters in alphabet.");
        }
        
        cipher = removeWhiteSpaces(cipher);
        alphabet = removeWhiteSpaces(alphabet);
        
        key = replaceNegatives(key, alphabet.length());
        
        while(cipher.length() % key.length != 0) {
            cipher += DUMMY_CHAR;
            counter++;
        }
        log.info("counter: " + Integer.toString(counter));
        
        String[] blocks = divideText(cipher, key.length);
        int[][] textMatrix = new int[key.length][blocks.length];
        
        for(int i = 0; i < blocks.length; ++i) {
            int[] temp = numericalEquivalent(blocks[i], alphabet);
            for(int j = 0; j < key.length; ++j) {
                textMatrix[j][i] = temp[j];
            }
        }
        
        textMatrix = multiply(textMatrix, inverse(key, alphabet.length()), alphabet.length());
        String result = stringEquivalent(textMatrix, alphabet);
        result = result.substring(0, result.length() - counter);
        
        return new Result(result);
    }

    @Override
    public List<Result> decipherWithoutKey(String cipher) {
        return null;
    }
    
    /*
    Divides openText into array od string with given size.
    */
    public String[] divideText(String openText, int size) {
        int quantity = openText.length() / size;
        String[] blocks = new String[quantity];
        
        for(int i = 0; i < quantity; ++i) {
            blocks[i] = openText.substring(i * size, (i + 1) * size);
            //log.info(blocks[i]);
        }
        
        
        return blocks;
    }
    
    /*
    For each letter in given string 'text', function finds its numerical
        equivalent in alphabet.
    */
    public int[] numericalEquivalent(String text, String alphabet) {
        int[] block = new int[text.length()];
        
        for(int i = 0; i < text.length(); ++i) {
            char letter = text.charAt(i);
            int index = alphabet.indexOf(letter);
            block[i] = index;
        }
        
        return block;
    }
    
    /*
    Function which muliplies two matrices.
    */
    public int[][] multiply(int[][] numEquivalents, int[][] key, int modulo) {
        int[][] result = new int[key.length][numEquivalents[0].length];
        
        for(int i = 0; i < key.length; ++i) {
            for(int j = 0; j < numEquivalents[i].length; ++j) {
                for(int k = 0; k < key.length; ++k) {
                    result[i][j] = (result[i][j] + key[i][k] * numEquivalents[k][j]) % modulo;
                }
            }
        }
        
        for(int i = 0; i< result.length; ++i) {
            String s = "";
            for(int j = 0; j< result[i].length; ++j) {
                s += Integer.toString(result[i][j]) + " ";
            }
            log.info(s); 
        }
        
        return result;
    }
    
    /*
    For every number in matrix, function finds its character equivalent and adds
    it to the resulting string. Columns represent correct order of strings.
    */
    public String stringEquivalent(int[][] cipher, String alphabet) {
        String result = "";
        
        for(int column = 0; column < cipher[0].length; ++column) {
            for(int row = 0; row < cipher.length; ++row) {
                result += alphabet.charAt(cipher[row][column]);
            }
        }
        
        return result;
    }
    
    
    
    /*
    Removes white spaces in the given openText.
    */
    public String removeWhiteSpaces(String text) {
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < text.length(); ++i) {
            if(!Character.isWhitespace(text.charAt(i))) {
                builder.append(text.charAt(i));
            }
        }
        return builder.toString();
    }
    
    /*
    Creates modular matrix inverse.
    */
    public int[][] inverse(int[][] input, int modulo) throws Exception{
        int n = input.length;
        
        input = replaceNegatives(input, modulo);
        int result = determinant(input, n);
        if(result == 0) {
            throw new Exception("Matrix is singular");
        }
        
        result = Math.floorMod(result, modulo);
        int modInverse = modularInverse(result, modulo);
        if(modInverse == 0) {
            throw new Exception("Matrix is not invertible with given alphabet length.");
        }
        log.info(Integer.toString(modInverse));
        
        int[][] inverseMatrix = new int[n][n];
        
        adjoint(input);
        
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                inverseMatrix[i][j] = (Math.floorMod(adjMatrix[i][j], modulo) * modInverse) % modulo; 
            }
        }
        
        for(int i = 0; i< inverseMatrix.length; ++i) {
                    String s = "";
                    for(int j = 0; j< inverseMatrix[i].length; ++j) {
                        s += Integer.toString(inverseMatrix[i][j]) + " ";
                    }
                    log.info(s); 
                }
        
        return inverseMatrix;
    }
    
    /*
    Recursive function which calculates determinant of the given matrix.
    */
    public int determinant(int[][] input, int n) {
        int result = 0;
        int sign = 1; //multiplier
        
        if(n == 1) {
            return input[0][0];
        }
        
        int[][] cofactors = new int[n][n];
        
        for(int i = 0; i < n; ++i) {
            cofactors = setCofactor(input, cofactors, 0, i, n);
            result += sign * input[0][i] * determinant(cofactors, n-1);
            sign = -sign;            
        }
        
        return result;
    }
    
    /*
    cofactors is matrix with elements which are required for calculating
        cofactor of number in matrix 'input' at position 'p', 'q'.
    */
    public int[][] setCofactor(int[][] input, int[][] cofactors, int p, int q, int n) {
        int i = 0, j = 0;
 
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                //Not in the given row and column
                if (row != p && col != q) {
                    cofactors[i][j++] = input[row][col];

                    // Row is filled, increase row index and reset col index
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
        
        return cofactors;
    }
    
    /*
    Fills 'adjMatrix' with adjoints of input.
    */
    public void adjoint(int[][] input) {
        int n = input.length;
        adjMatrix = new int[n][n];
        
        if(n == 1) {
            adjMatrix[0][0] = 1;
            return;
        }
        
        int[][] cofactors = new int[n][n];
        
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                cofactors = setCofactor(input, cofactors, i, j, n);
                
                // sign of adj[j][i] positive if sum of row
                // and column indexes is even.
                int sign = ((i + j) % 2 == 0)? 1: -1;
                adjMatrix[j][i] = (sign)*(determinant(cofactors, n-1));
            }
        }
        
        for(int i = 0; i< cofactors.length; ++i) {
            String s = "";
            for(int j = 0; j< cofactors[i].length; ++j) {
                s += Integer.toString(cofactors[i][j]) + " ";
            }
            log.info(s); 
        }
    }
    
    /*
    Replaces negative number in given matrix with positive ones modulo 'modulo'.
    */
    public int[][] replaceNegatives(int[][] input, int modulo) {
        for(int i = 0; i < input.length; ++i) {
            for(int j = 0; j < input[i].length; ++j) {
                if(input[i][j] < 0) {
                    input[i][j] = Math.floorMod(input[i][j], modulo);
                }
            }
        }
        
        return input;
    }
    
    /*
    Finds modular inverse of given number.
    */
    public int modularInverse(int number, int modulo) {
        for(int i = 1; i < modulo; ++i) {
            if (((number % modulo) * (i % modulo)) % modulo == 1)
            return i;
        }
        
        return 0;
    }
}
