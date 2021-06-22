package com.java.crypto.controllers;

import com.java.crypto.domain.Result;
import com.java.crypto.services.HillCipherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/hill")
@CrossOrigin(origins = "http://localhost:4200")
public class HillCipherController {

    private final HillCipherService hillCipherService;

    public HillCipherController(HillCipherService hillCipherService) {
        this.hillCipherService = hillCipherService;
    }

    @GetMapping
    @RequestMapping("/cipher")
    public ResponseEntity<Result> hillCipher(@RequestParam String openText, @RequestParam Integer[][] key, @RequestParam String alphabet) {
        Result response = hillCipherService.cipher(openText, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<Result> hillDecipher(@RequestParam String cipher, @RequestParam Integer[][] key, @RequestParam String alphabet) {
        Result response = hillCipherService.decipher(cipher, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipherNoKey")
    public ResponseEntity<List<Result>> hillDecipher(@RequestParam String cipher) {
        List<Result> response = hillCipherService.decipherWithoutKey(cipher);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
