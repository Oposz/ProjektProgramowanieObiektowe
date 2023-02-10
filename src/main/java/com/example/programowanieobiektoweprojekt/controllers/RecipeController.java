package com.example.programowanieobiektoweprojekt.controllers;

import com.example.programowanieobiektoweprojekt.models.Ingredient;
import com.example.programowanieobiektoweprojekt.models.Recipe;
import com.example.programowanieobiektoweprojekt.models.Step;
import com.example.programowanieobiektoweprojekt.models.Tag;
import com.example.programowanieobiektoweprojekt.repositories.IngredientRepository;
import com.example.programowanieobiektoweprojekt.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@CrossOrigin(origins = "http://localhost:8081")
@Controller
public class RecipeController {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    StepRepository stepRepository;
    @Autowired
    TagRepository tagRepository;

    @GetMapping("/recipes")
    public String recipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }

    @GetMapping("/recipes/{id}")
    public String recipe(@PathVariable Integer id, Model model) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("step", new Step());
        model.addAttribute("tag", new Tag());
        return "recipe";
    }

    @GetMapping("/recipe-add-template")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-add-template";
    }

    @GetMapping("/recipe-preview/{id}")
    public String previewRecipe(Model model, @PathVariable Integer id) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        return "recipe-preview";
    }

    @GetMapping("/recipe-update/{id}")
    public String updateRecipe(Model model, @PathVariable Integer id) {
        model.addAttribute("recipe", recipeRepository.findById(id).orElse(null));
        model.addAttribute("step", new Step());
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("tag", new Tag());
        return "recipe-update";
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
    public String addIngredient(@PathVariable Integer id, @ModelAttribute Ingredient ingredient) {
        var existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isEmpty()) {
            return "redirect:/recipes/" + id;

        }
        var recipeToUpdate = existingRecipe.get();
        var existingIngredient = ingredientRepository.findByName(ingredient.getName().toLowerCase());

        if (existingIngredient.isEmpty()) {
            ingredient.setName(ingredient.getName().toLowerCase());
            ingredient.setCreatedAt(LocalDateTime.now());
            ingredient.setUpdatedAt(LocalDateTime.now());
            ingredientRepository.save(ingredient);
            recipeToUpdate.addIngredient(ingredient);
            recipeRepository.save(recipeToUpdate);
            return "redirect:/recipes/" + id;
        }
        if(existingIngredient.get().getName().equals(ingredient.getName())){
             return "redirect:/recipes/" + id;
        }
        recipeToUpdate.addIngredient(existingIngredient.get());
        recipeRepository.save(recipeToUpdate);
        return "redirect:/recipes/" + id;

    }

    @PostMapping("/recipes/add-step/{id}")
    public String addStep(@PathVariable Integer id, @ModelAttribute Step step) {
        var recipe = recipeRepository.findById(id).orElse(null);
        step.setCreatedAt(LocalDateTime.now());
        step.setUpdatedAt(LocalDateTime.now());
        step.setRecipeId(id);
        assert recipe != null;
        step.setStep(recipe.getSteps().size() + 1);
        recipe.addStep(step);
        recipeRepository.save(recipe);
        return "redirect:/recipes/" + id;
    }

    // TODO:
    // 8. docs
}
