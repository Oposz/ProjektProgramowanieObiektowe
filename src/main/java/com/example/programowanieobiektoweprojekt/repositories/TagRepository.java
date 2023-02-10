package com.example.programowanieobiektoweprojekt.repositories;

import com.example.programowanieobiektoweprojekt.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByNameContaining(String name);
}