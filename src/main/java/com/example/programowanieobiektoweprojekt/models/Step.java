package com.example.programowanieobiektoweprojekt.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RecipeStep")
public class Step extends TimeData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "step")
    private String step;
    @Column(name = "description")
    private String description;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getRecipeId() {
        return recipeId;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", step=" + step +
                ", description='" + description + '\'' +
                ", recipeId='" + recipeId + '\'' +
                '}';
    }
}
