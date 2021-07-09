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
    public ResponseEntity<Result> hillCipher(@RequestParam String openText, @RequestParam String key, @RequestParam String alphabet) {
        Result response;
        
        try{
            response = hillCipherService.cipher(openText, key, alphabet);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //Result response = hillCipherService.cipher(openText, key, alphabet);

        //return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<Result> hillDecipher(@RequestParam String cipher, @RequestParam String key, @RequestParam String alphabet) throws Exception {
        Result response = hillCipherService.decipher(cipher, key, alphabet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/decipherNoKey")
    public ResponseEntity<List<Result>> hillDecipher(@RequestParam String cipher) throws Exception {
        List<Result> response = hillCipherService.decipherWithoutKey(cipher);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
