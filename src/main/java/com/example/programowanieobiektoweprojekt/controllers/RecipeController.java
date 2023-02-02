package com.example.programowanieobiektoweprojekt.controllers;

import com.example.programowanieobiektoweprojekt.models.Recipe;
import com.example.programowanieobiektoweprojekt.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:8081")
@Controller
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;


    @GetMapping("/recipes")
    public String recipes(Model model) {
        List<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        model.addAttribute("recipe", new Recipe());
        return "recipes";
    }


    @PostMapping("/recipes")
    public String recipes(@ModelAttribute Recipe recipes) {
        recipeRepository.save(recipes);
        return "redirect:/recipes";
    }

    @PostMapping("/deleteRecipe")
    public String deleteRecipe(@RequestParam Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/recipes";
    }
}
