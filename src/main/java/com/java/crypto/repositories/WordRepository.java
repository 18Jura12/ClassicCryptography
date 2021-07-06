package com.java.crypto.repositories;

import com.java.crypto.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, String> {
    Optional<List<Word>> findAllByKind(@Param("kind") String kind);
    Optional<List<Word>> findAllByWordStartsWith(@Param("part") String part);
    Optional<Word> findByWord(@Param("word") String word);
    boolean existsByWord(@Param("word") String word);
    boolean existsByWordAndKind(@Param("word") String word, @Param("kind") String kind);
}
