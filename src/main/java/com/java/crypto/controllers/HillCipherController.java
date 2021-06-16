package com.java.crypto.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/hill")
@CrossOrigin(origins = "http://localhost:4200")
public class HillCipherController {

    @GetMapping
    @RequestMapping("/cipher")
    public ResponseEntity<String> hillCipher(@RequestBody String openText, @PathVariable("key")Integer[][] key, @PathVariable("alphabet") String alphabet) {
        return null;
    }

    @GetMapping
    @RequestMapping("/decipher")
    public ResponseEntity<String> hillDecipher(@RequestBody String cipher, @PathVariable("key")String key, @PathVariable("alphabet") String alphabet) {
        return null;
    }

    @GetMapping
    @RequestMapping("/decipherNoKey")
    public ResponseEntity<List<String>> hillDecipher(@RequestBody String cipher, @PathVariable("alphabet") String alphabet) {
        return null;
    }
}
