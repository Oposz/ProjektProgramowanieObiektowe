package com.example.programowanieobiektoweprojekt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.MappedSuperclass;


import java.time.LocalDateTime;

@MappedSuperclass
public class TimeData {
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "TimeData{" +
                "createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
