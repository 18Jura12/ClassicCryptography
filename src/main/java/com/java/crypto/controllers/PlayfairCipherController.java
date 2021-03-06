package com.java.crypto.controllers;

import com.java.crypto.domain.ExtendedResult;
import com.java.crypto.services.PlayfairCipherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/playfair")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayfairCipherController {

    private final PlayfairCipherService playfairCipherService;

    public PlayfairCipherController(PlayfairCipherService playfairCipherService) {
        this.playfairCipherService = playfairCipherService;
    }

    @GetMapping
    @RequestMapping("/cipher")
    public ResponseEntity<ExtendedResult> playfairCipher(@RequestParam String openText, @RequestParam String key, @RequestParam String language) {
        ExtendedResult response;
        
        try{
            response = playfairCipherService.cipher(openText, key, language);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //Result response = playfairCipherService.cipher(openText, key, language);

        //return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<ExtendedResult> playfairDecipher(@RequestParam String cipher, @RequestParam String key, @RequestParam String language) {
        ExtendedResult response;
        
        try{
            response = playfairCipherService.decipher(cipher, key, language);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //Result response = playfairCipherService.decipher(cipher, key, language);

        //return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
