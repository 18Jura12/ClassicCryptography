package com.java.crypto.services;

import com.java.crypto.repositories.WordRepository;
import org.springframework.stereotype.Service;

@Service("InitializeService")
public class InitializeServiceImpl implements InitializeService {

    private final WordRepository wordRepository;

    public InitializeServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }


    @Override
    public void initialize() {
        //Ovdje napuni bazu INES!!!
    }
}
