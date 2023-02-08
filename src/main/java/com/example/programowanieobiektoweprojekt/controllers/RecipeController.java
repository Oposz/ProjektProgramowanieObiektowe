package com.example.programowanieobiektoweprojekt.controllers;

import com.example.programowanieobiektoweprojekt.models.Ingredient;
import com.example.programowanieobiektoweprojekt.models.Recipe;
import com.example.programowanieobiektoweprojekt.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081")
@Controller
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }

    @GetMapping("/recipes/{id}")
    public String recipe(@PathVariable Integer id, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        return "recipe";
    }

    @GetMapping("/recipe-add-template")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-add-template";
    }

    @PostMapping("/recipes/add")
    public String createRecipe(@ModelAttribute Recipe recipe) {
        var created = recipeRepository.save(recipe);
        return "redirect:/recipes/" + created.getId();
    }

    @PostMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable Integer id) {
        recipeRepository.deleteById(id);
        return "redirect:/recipes";
    }

    @PostMapping("/recipes/add-ingredient/{id}")
    // TODO: add ingredient to model
    public String addIngredient(@PathVariable Ingredient id) {
        // check if ingredient already exists in database
        // if not, add it and assign to recipe
        // if yes, assign existing ingredient to recipe
        return "redirect:/recipes";
    }

    @PostMapping("/recipes/add-step/{id}")
    // TODO: add step to model
    public String addStep(@PathVariable Ingredient id) {
        // create new step and assign to recipe
        return "redirect:/recipes";
    }

    // TODO:
    // 1. dodac dodawanie ingrdientsow i stepow na widoku recipe
    // 2. zrobic wyswietlanie przepisu
    // 3. zrobic opcje update przepisu
    // 4. rejestracja usera
    // 5. logowanie
    // 6. sprawdzanie czy to twoj przepis inaczej nie mozesz zrobic update
    // 7. wyszukiwanie przepisu po nazwie
    // 8. docs
}
