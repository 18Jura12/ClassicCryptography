package com.java.crypto.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/playfair")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayfairCipherController {

    @GetMapping
    @RequestMapping("/cipher")
    public ResponseEntity<String> playfairCipher(@RequestBody String openText, @PathVariable("key")String key, @PathVariable("alphabet") String alphabet) {
        return null;
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<String> playfairDecipher(@RequestBody String cipher, @PathVariable("key")String key, @PathVariable("alphabet") String alphabet) {
        return null;
    }
}
