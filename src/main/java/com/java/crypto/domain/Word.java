package com.java.crypto.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Word {

    @Id
    private String word;

    private String kind;

    public Word() {}

    public Word(String word, String kind) {
        this.word = word;
        this.kind = kind;
    }

}
