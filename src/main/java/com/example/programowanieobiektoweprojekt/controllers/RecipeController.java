package com.example.programowanieobiektoweprojekt.controllers;

import com.example.programowanieobiektoweprojekt.models.Ingredient;
import com.example.programowanieobiektoweprojekt.models.Recipe;
import com.example.programowanieobiektoweprojekt.models.Step;
import com.example.programowanieobiektoweprojekt.models.Tag;
import com.example.programowanieobiektoweprojekt.repositories.IngredientRepository;
import com.example.programowanieobiektoweprojekt.repositories.RecipeRepository;
import com.example.programowanieobiektoweprojekt.repositories.StepRepository;
import com.example.programowanieobiektoweprojekt.repositories.TagRepository;
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
        System.out.println(recipe);
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setUpdatedAt(LocalDateTime.now());
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

        if (existingRecipe.get().getIngredients().contains(existingIngredient.get())){
            return "redirect:/recipes/" + id;
        }

        recipeToUpdate.addIngredient(existingIngredient.get());
        recipeRepository.save(recipeToUpdate);
        return "redirect:/recipes/" + id;

    }

    @PostMapping("/recipes/remove-ingredient/{id}")
    public String removeIngredient(@PathVariable Integer id, @RequestParam Integer ingredientId) {
        var existingIngredient = ingredientRepository.findById(ingredientId);
        var existingRecipe = recipeRepository.findById(id);

        if (existingIngredient.isEmpty()) {
            return "redirect:/recipes/" + id;
        }
        if (existingRecipe.isEmpty()) {
            return "redirect:/recipes/" + id;
        }
        existingRecipe.get().removeIngredient(existingIngredient.get());
        recipeRepository.save(existingRecipe.get());
        return "redirect:/recipes/" + id;
    }



    @PostMapping("/recipes/remove-tag/{id}")
    public String removeTag(@PathVariable Integer id, @RequestParam Integer tagId) {
        var existingTag = tagRepository.findById(tagId);
        var existingRecipe = recipeRepository.findById(id);

        if (existingTag.isEmpty()) {
            return "redirect:/recipes/" + id;
        }
        if (existingRecipe.isEmpty()) {
            return "redirect:/recipes/" + id;
        }
        existingRecipe.get().removeTag(existingTag.get());
        recipeRepository.save(existingRecipe.get());
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

    @PostMapping("/recipes/add-tag/{id}")
    public String  addTag(@PathVariable Integer id, @ModelAttribute Tag tag) {
        var existingRecipe = recipeRepository.findById(id);
        if (existingRecipe.isEmpty()){
            return "redirect:/recipes/" + id;
        }
        var existingTag = tagRepository.findByName(tag.getName());
        if(existingTag.isEmpty()) {
            tag.setCreatedAt(LocalDateTime.now());
            tag.setUpdatedAt(LocalDateTime.now());
            tagRepository.save(tag);
            existingRecipe.get().addTag(tag);
            recipeRepository.save(existingRecipe.get());
            return "redirect:/recipes/" + id;
        }
        if (existingRecipe.get().getTags().contains(existingTag.get())){
            return "redirect:/recipes/" + id;
        }
        existingRecipe.get().addTag(existingTag.get());
        recipeRepository.save(existingRecipe.get());
        return "redirect:/recipes/" + id;
    }



    @PostMapping("/recipes/remove-step/{id}")
    public String removeStep(@PathVariable Integer id, @RequestParam Integer stepId) {
        stepRepository.deleteById(stepId);
        var recipe = recipeRepository.findById(id).orElse(null);
        var steps = recipe.getSteps();
        for (int i = 0; i < steps.size(); i++) {
            steps.get(i).setStep(i + 1);
        }
        recipe.setSteps(steps);
        recipeRepository.save(recipe);
        return "redirect:/recipes/" + id;
    }

    @PostMapping("/recipes/update-ingredient/{id}")
    public String updateIngredient(
            @PathVariable Integer id,
            @ModelAttribute Ingredient ingredient,
            @RequestParam Integer oldIngredientId
    ) {
        var existingRecipe = recipeRepository.findById(id);
        var oldIngredient = ingredientRepository.findById(oldIngredientId);
        if (oldIngredient.isEmpty()) {
            return "redirect:/recipe-update/" + id;
        }
        if (existingRecipe.isEmpty()) {
            return "redirect:/recipe-update/" + id;
        }
        var updatedRecipe = existingRecipe.get();

        var existingIngredient = ingredientRepository.findByName(ingredient.getName());
        if (existingIngredient.isEmpty()) {
            ingredient.setCreatedAt(LocalDateTime.now());
            ingredient.setUpdatedAt(LocalDateTime.now());
            ingredientRepository.save(ingredient);
            updatedRecipe.addIngredient(ingredient);
            updatedRecipe.removeIngredient(oldIngredient.get());
            recipeRepository.save(updatedRecipe);

            return "redirect:/recipe-update/" + id;
        }
        if(existingRecipe.get().getIngredients().contains(existingIngredient.get())){
            return "redirect:/recipe-update/" + id;
        }
        updatedRecipe.removeIngredient(oldIngredient.get());
        updatedRecipe.addIngredient(existingIngredient.get());

        recipeRepository.save(updatedRecipe);
        return "redirect:/recipe-update/" + id;
    }

    @PostMapping("/recipes/update-step/{id}")
    public String updateStep(
            @PathVariable Integer id,
            @ModelAttribute Step step,
            @RequestParam Integer oldStepId
    ) {
        var oldStep = stepRepository.findById(oldStepId);
        if (oldStep.isEmpty()) {
            return "redirect:/recipe-update/" + id;
        }
        var existingStep = stepRepository.findById(oldStepId);
        var existingStepData = existingStep.get();
        existingStepData.setRecipeId(id);
        existingStep.get().setDescription(step.getDescription());
        stepRepository.save(existingStep.get());
        return "redirect:/recipe-update/" + id;
    }

}
