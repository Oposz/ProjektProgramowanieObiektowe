package com.example.programowanieobiektoweprojekt.controllers;

import com.example.programowanieobiektoweprojekt.models.Ingredient;
import com.example.programowanieobiektoweprojekt.models.Recipe;
import com.example.programowanieobiektoweprojekt.repositories.IngredientRepository;
import com.example.programowanieobiektoweprojekt.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@Controller
public class IngredientController {
    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("/ingredinets")
    public String ingredients(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients";
    }

    @GetMapping("/ingredinets/{id}")
    public String ingredient(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).orElse(null));
        return "ingredients";
    }

    @PostMapping("/ingredinets")
    public String createIngredient(@ModelAttribute Ingredient ingredient) {
        ingredientRepository.save(ingredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/ingredinets/{id}")
    public String deleteIngredient(@PathVariable Integer id) {
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }
}
