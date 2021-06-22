package com.java.crypto.controllers;

import com.java.crypto.domain.Result;
import com.java.crypto.services.CaesarCipherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("api/caesar")
@CrossOrigin(origins = "http://localhost:4200")
public class CaesarCipherController {

    private final CaesarCipherService caesarCipherService;

    public CaesarCipherController(CaesarCipherService caesarCipherService) {
        this.caesarCipherService = caesarCipherService;
    }

    @GetMapping
    @RequestMapping("/cipher")
    public ResponseEntity<Result> caesarCipher(@RequestParam String openText, @RequestParam Integer shift, @RequestParam String alphabet) throws IOException {
        Result response = caesarCipherService.cipher(openText, shift, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<Result> caesarDecipher(@RequestParam String cipher, @RequestParam Integer shift, @RequestParam String alphabet) throws IOException {
        Result response = caesarCipherService.decipher(cipher, shift, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
