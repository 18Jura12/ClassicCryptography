package com.java.crypto.services;

import java.util.LinkedHashSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PlayfairCipherServiceImpl implements PlayfairCipherService {
    
    private static final char DUMMY_CHAR = 'X';
    private static final String CROATIAN = "CROATIAN";
    private static final String ENGLISH = "ENGLISH";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public char[][] matrix;

    @Override
    public String cipher(String openText, String key, String language) {
        openText = openText.toUpperCase();
        key = key.toUpperCase();
        
        openText = removeWhiteSpaces(openText); 
        key = removeWhiteSpaces(key);
        openText = processDummyChar(openText);
        openText = formatText(openText, language);
        // in case IJ -> II
        openText = processDummyChar(openText);
        
        key = prepareKey(key, language);
        createMatrix(key, language);
        
        String[] blocks = makeBlocks(openText);
        
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < blocks.length; ++i) {
            char first = blocks[i].charAt(0);
            char second = blocks[i].charAt(1);
            
            int[] positionFirst = getPosition(first);
            int[] positionSecond = getPosition(second);
            
            //three cases
            if(positionFirst[0] == positionSecond[0]) {
                builder.append(matrix[positionFirst[0]][(positionFirst[1] + 1) % 5]);
                builder.append(matrix[positionSecond[0]][(positionSecond[1] + 1) % 5]);
            } else if(positionFirst[1] == positionSecond[1]) {
                builder.append(matrix[(positionFirst[0] + 1) % 5][positionFirst[1]]);
                builder.append(matrix[(positionSecond[0] + 1) % 5][positionSecond[1]]);
            } else {
                builder.append(matrix[positionFirst[0]][positionSecond[1]]);
                builder.append(matrix[positionSecond[0]][positionFirst[1]]);
            }
        }
        
        return builder.toString();
    }

    @Override
    public String decipher(String cipher, String key, String language) {
        cipher = cipher.toUpperCase();
        key = key.toUpperCase();
        
        cipher = removeWhiteSpaces(cipher); 
        key = removeWhiteSpaces(key);
        cipher = processDummyChar(cipher);
        cipher = formatText(cipher, language);
        // in case IJ -> II
        cipher = processDummyChar(cipher);
        
        key = prepareKey(key, language);
        createMatrix(key, language);
        
        String[] blocks = makeBlocks(cipher);
        
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < blocks.length; ++i) {
            char first = blocks[i].charAt(0);
            char second = blocks[i].charAt(1);
            
            int[] positionFirst = getPosition(first);
            int[] positionSecond = getPosition(second);
            
            //three cases
            if(positionFirst[0] == positionSecond[0]) {
                builder.append(matrix[positionFirst[0]][(positionFirst[1] + 4) % 5]);
                builder.append(matrix[positionSecond[0]][(positionSecond[1] + 4) % 5]);
            } else if(positionFirst[1] == positionSecond[1]) {
                builder.append(matrix[(positionFirst[0] + 4) % 5][positionFirst[1]]);
                builder.append(matrix[(positionSecond[0] + 4) % 5][positionSecond[1]]);
            } else {
                builder.append(matrix[positionFirst[0]][positionSecond[1]]);
                builder.append(matrix[positionSecond[0]][positionFirst[1]]);
            }
        }
        
        return builder.toString();
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
    If two letter are equal (side by side), then we add dummyChar next to each letter
    */
    public String processDummyChar(String text) {
        if(text.length() % 2 == 1) {
            text += DUMMY_CHAR;
        }
        
        StringBuilder builder = new StringBuilder();
        
        for(int i = 0; i < text.length(); i += 2) {
            if(text.charAt(i) == text.charAt(i + 1)) {
                builder.append(text.charAt(i));
                builder.append(DUMMY_CHAR);
                builder.append(text.charAt(i));
                builder.append(DUMMY_CHAR);
            } else {
                builder.append(text.charAt(i));
                builder.append(text.charAt(i + 1));
            }
        }
        return builder.toString();
    }
    
    /*
    Prepares text (openText). If text contains specific (J for eng) letter, we replace it with the correct one.
    */
    public String formatText(String text, String language) {
        char replaceChar;
        String replaceWith;
        
        if(language == CROATIAN) {
            replaceChar = 'W';
            replaceWith = "V";
        } else { //ENGLISH
            replaceChar = 'J';
            replaceWith = "I";
        }
        
        StringBuilder builder = new StringBuilder(text);
        
        for(int i = 0; i < text.length(); ++i) {
            if(text.charAt(i) == replaceChar) {
                builder.replace(i, i+1, replaceWith);
            }
        }    
        
        return builder.toString();
    }
    
    /*
    Removes duplicate characters from key.
    */
    public String prepareKey(String key, String language) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        
        key = removeWhiteSpaces(key);
        key = formatText(key, language);
        
        for(int i = 0; i < key.length(); ++i) {
            set.add(key.charAt(i));
        }
        
        StringBuilder builder = new StringBuilder();
        
        for (Character temp : set) {
            builder.append(temp.toString());
        }
        
        return builder.toString();
    }
    
    /*
    Creates Playfair matrix using the given key.
    */
    public void createMatrix(String key, String language) {
        matrix = new char[5][5];
        String alphabet = ALPHABET;
        
        if(language == CROATIAN) {
            alphabet = alphabet.replace("W", "");
        } else { //ENGLISH
            alphabet = alphabet.replace("J", "");
        }
        
        StringBuilder builder = new StringBuilder(key);
        
        for(int i = 0; i < alphabet.length(); ++i) {
            if(!key.contains(String.valueOf(alphabet.charAt(i)))) {
                builder.append(alphabet.charAt(i));
            }
        }
        
        String result = builder.toString();
        
        for(int i = 0, k = 0; i < 5; ++i) {
            for(int j = 0; j < 5; ++j) {
                matrix[i][j] = result.charAt(k++);
            }
        }
    }
    
    /*
    Makes block of two letters for given text
    */
    public String[] makeBlocks(String text) {
        //it is even
        String[] blocks = new String[text.length() / 2];
        
        for(int i = 0, textCount = 0; i < text.length() / 2; ++i) {
            blocks[i] = text.substring(textCount, textCount += 2);
        }
        
        return blocks;
    }
    
    public int[] getPosition(char letter) {
        // first is row, second column
        int [] position = new int[2];
        
        for(int i = 0; i < 5; ++i) {      
            for(int j = 0; j < 5; ++j) {
                if(matrix[i][j] == letter) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        
        return null;
    }
}
