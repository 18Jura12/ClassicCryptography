package com.java.crypto.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@Entity
public class Word {

    @Id
    private String word;

    private String kind;

    public Word() {}

    public Word(String word, String kind) {
        this.word = word;
        this.kind = kind;;
    }
    
}
