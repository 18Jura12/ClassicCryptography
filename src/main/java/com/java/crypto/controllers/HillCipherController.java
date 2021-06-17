package com.java.crypto.controllers;

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
    public ResponseEntity<String> hillCipher(@RequestBody String openText, @PathVariable("key")Integer[][] key, @PathVariable("alphabet") String alphabet) {
        String response = hillCipherService.cipher(openText, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<String> hillDecipher(@RequestBody String cipher, @PathVariable("key")Integer[][] key, @PathVariable("alphabet") String alphabet) {
        String response = hillCipherService.decipher(cipher, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipherNoKey")
    public ResponseEntity<List<String>> hillDecipher(@RequestBody String cipher) {
        List<String> response = hillCipherService.decipherWithoutKey(cipher);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
