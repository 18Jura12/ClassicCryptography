package com.java.crypto.services;

import com.java.crypto.domain.Word;
import com.java.crypto.repositories.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> findWordsByKind(String kind) {
        return wordRepository.findAllByKind(kind).get();
    }

    @Override
    public List<Word> findWordsStartingWith(String part) {
        return wordRepository.findAllByWordStartsWith(part).get();
    }

    @Override
    public Word findByWord(String word) {
        return wordRepository.findByWord(word).get();
    }

    @Override
    public boolean existsByWord(String word) {
        return wordRepository.existsByWord(word);
    }
}
