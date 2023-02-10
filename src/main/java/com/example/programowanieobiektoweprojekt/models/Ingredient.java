package com.example.programowanieobiektoweprojekt.models;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Ingredient")
public class Ingredient extends BaseData{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                '}';
    }
}
