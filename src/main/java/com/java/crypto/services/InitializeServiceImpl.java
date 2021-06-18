package com.java.crypto.services;

import com.java.crypto.domain.Word;
import com.java.crypto.repositories.WordRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("InitializeService")
public class InitializeServiceImpl implements InitializeService {

    private final WordRepository wordRepository;

    public InitializeServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }


    @Override
    public void initialize() throws Exception{
        log.info("Initializing database...");
        Path path = Paths.get("src/main/resources/rjecnik.txt");

        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(path);
        } catch (IOException ex) {
            log.error(ex.toString());
        }
        
        String line;
        while((line = reader.readLine()) != null) {
            String arr[] = line.split("\\s+");
            Word word = new Word(arr[0], arr[arr.length-1]);
            wordRepository.save(word);                
        }
        
        log.info("Database initialized.");
    }
}
