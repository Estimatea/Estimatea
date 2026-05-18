package com.example.estimatea.service;

import com.example.estimatea.model.Complexity;
import com.example.estimatea.repository.jdbc.ComplexityRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComplexityServiceTest {

    @Mock
    private ComplexityRepository complexityRepository;

   @InjectMocks
   private ComplexityService complexityService;
   private Complexity complexity;



}
