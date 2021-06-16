package com.java.crypto.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/caesar")
@CrossOrigin(origins = "http://localhost:4200")
public class CaesarCipherController {

    @GetMapping
    @RequestMapping("/cipher")
    public ResponseEntity<String> caesarCipher(@RequestBody String openText, @PathVariable("shift")Integer shift, @PathVariable("alphabet") String alphabet) {
        return null;
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<String> caesarDecipher(@RequestBody String cipher, @PathVariable("alphabet") String alphabet) {
        return null;
    }

}
