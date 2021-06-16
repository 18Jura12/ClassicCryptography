package com.java.crypto.services;

import com.java.crypto.domain.Word;

import java.util.List;

public interface WordService {
    List<Word> findWordsByKind(String kind);
    List<Word> findWordsStartingWith(String part);
    Word findByWord(String word);
    boolean existsByWord(String word);
}
