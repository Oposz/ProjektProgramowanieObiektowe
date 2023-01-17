package com.example.programowanieobiektoweprojekt.controllers;

import com.example.programowanieobiektoweprojekt.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.programowanieobiektoweprojekt.models.Recipe;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;
    @GetMapping("/recipes")
    public List<Recipe> index(){
        return recipeRepository.findAll();
    }
}
