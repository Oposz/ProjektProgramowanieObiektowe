package com.example.programowanieobiektoweprojekt.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "Recipe")
public class Recipe extends BaseData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ownerId")
    private int ownerId;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
    @ManyToMany
    @JoinTable(
            name = "_IngredientToRecipe",
            joinColumns = @JoinColumn(name = "B"),
            inverseJoinColumns = @JoinColumn(name = "A"))
    private List<Ingredient> ingredients;
    @OneToMany
    @JoinColumn(name = "recipeId")
    private List<Step> steps;

    public Recipe() {
    }


    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void removeStep(Step step) {
        this.steps.remove(step);
    }


    public void addStep(Step step) {
        this.steps.add(step);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public List<Step> getSteps() {
        return steps;
    }


    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                '}';
    }
}
