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
@Controller
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;


    @GetMapping("/recipes-list")
    public String recipesList(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        return "recipes-list";
    }

    @GetMapping("/recipe-add-template")
    public String addRecipeRedirect(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-add-template";
    }

    @GetMapping("/recipe/{id}")
    public String recipe(@PathVariable Integer id, Model model) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        model.addAttribute("recipe", recipe.orElse(null));
        return "recipe";
    }

    @GetMapping("/recipe-edit/{id}")
    public String recipe(@PathVariable Integer id) {
//        Optional<Recipe> recipe = recipeRepository.findById(id);
//        model.addAttribute("recipe", recipe.orElse(null));
        return "recipe-edit";
    }


    @PostMapping("/recipe-add")
    public String recipesList(@ModelAttribute Recipe recipes) {
        recipeRepository.save(recipes);
        return "redirect:/recipes-list";
    }

    @PostMapping("/deleteRecipe")
    public String deleteRecipe(@RequestParam Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/recipes-list";
    }
}
