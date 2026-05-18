package com.example.estimatea.service;

import com.example.estimatea.model.Complexity;
import com.example.estimatea.repository.jdbc.ComplexityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComplexityServiceTest {

   @Mock
   private ComplexityRepository complexityRepository;

   @InjectMocks
   private ComplexityService complexityService;
   private Complexity complexityMock;

   @BeforeEach
   public void setUp() {
        complexityMock = new Complexity(10 /* ID */, 10, "Enterprise Grade", 3.0);
   }

   @Test
    void shouldReturnAllComplexityScores() {
       when(complexityRepository.showAllComplexityScores()).thenReturn(List.of(complexityMock));

       List<Complexity> complexityList = complexityService.showAllComplexityScores();

       assertNotNull(complexityList);
       assertThat(complexityList.size() == 1);
       assertThat(complexityList.getFirst().getComplexityId()).isEqualTo(10);
       assertThat(complexityList.getFirst().getComplexityScore()).isEqualTo(10);
       assertThat(complexityList.getFirst().getLabelType()).isEqualTo("Enterprise Grade");
       assertThat(complexityList.getFirst().getRateMultiplier()).isEqualTo(3.0);

       verify(complexityRepository).showAllComplexityScores();
   }
}
