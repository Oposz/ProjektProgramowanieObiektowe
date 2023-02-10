package com.example.programowanieobiektoweprojekt.repositories;

import com.example.programowanieobiektoweprojekt.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StepRepository extends JpaRepository<Step, Integer> {
    List<Step> findByStepContaining(String step);
}
