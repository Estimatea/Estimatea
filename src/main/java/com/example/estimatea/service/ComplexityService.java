package com.example.estimatea.service;

import com.example.estimatea.model.Complexity;
import com.example.estimatea.repository.jdbc.ComplexityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplexityService {

    private final ComplexityRepository complexityRepository;

    public ComplexityService(ComplexityRepository complexityRepository) {
        this.complexityRepository = complexityRepository;
    }

    public List<Complexity> showAllComplexityScores() {
        return complexityRepository.showAllComplexityScores();
    }
}
