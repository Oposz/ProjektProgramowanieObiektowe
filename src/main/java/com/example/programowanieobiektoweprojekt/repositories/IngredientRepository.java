package com.example.programowanieobiektoweprojekt.repositories;

import com.example.programowanieobiektoweprojekt.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByNameContaining(String name);
}