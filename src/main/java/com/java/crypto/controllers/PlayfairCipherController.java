package com.java.crypto.controllers;

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
    public ResponseEntity<String> playfairCipher(@RequestBody String openText, @PathVariable("key")String key, @PathVariable("alphabet") String alphabet) {
        String response = playfairCipherService.cipher(openText, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<String> playfairDecipher(@RequestBody String cipher, @PathVariable("key")String key, @PathVariable("alphabet") String alphabet) {
        String response = playfairCipherService.decipher(cipher, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
