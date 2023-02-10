package com.example.programowanieobiektoweprojekt.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseData extends TimeData{
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "name='" + name + '\'' +
                '}';
    }
}
