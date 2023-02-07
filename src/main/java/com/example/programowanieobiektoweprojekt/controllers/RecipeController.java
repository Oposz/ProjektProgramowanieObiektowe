package com.example.programowanieobiektoweprojekt.controllers;

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
@Controller('/recipes')
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/")
    public String recpies(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }

    @GetMapping("/{id}")
    public String recipe(@PathVariable Integer id, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        return "recipe";
    }

    @PostMapping("/")
    public String createRecipe(@ModelAttribute Recipe recipe) {
        var created = recipeRepository.save(recipe);
        return "redirect:/recipes/" + created.getId();
    }

    @PostMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/recipes";
    }

    @PostMapping("/add-ingredient/{id}")
    // TODO: add ingredient to model
    public String addIngredient(@PathVariable Ingredient id) {
        // check if ingredient already exists in database
        // if not, add it and assign to recipe
        // if yes, assign existing ingredient to recipe
        return "redirect:/recipes";
    }

    @PostMapping("/add-step/{id}")
    // TODO: add step to model
    public String addStep(@PathVariable Ingredient id) {
        // create new step and assign to recipe
        return "redirect:/recipes";
    }
}
