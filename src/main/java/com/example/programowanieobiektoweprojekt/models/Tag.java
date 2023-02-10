package com.example.programowanieobiektoweprojekt.models;

import jakarta.persistence.*;


@Entity
@Table(name = "Tag")
public class Tag extends BaseData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                '}';
    }
}
