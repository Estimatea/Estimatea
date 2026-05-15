package com.example.estimatea.repository;
import com.example.estimatea.model.Complexity;
import com.example.estimatea.repository.jdbc.ComplexityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import java.net.URL;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ComplexityRepositoryTest {

    @Autowired
    private ComplexityRepository complexityRepo;

    @Test
    void contextLoads() {}

    @Test
    void checkH2schemaFile() {
        URL url = getClass().getClassLoader().getResource("h2init.sql");
        assertNotNull(url);
    }

    @Test
    void shouldShowAllComplexityScores() {
        List<Complexity> allComplexityInserts = complexityRepo.showAllComplexityScores();

        assertThat(allComplexityInserts).isNotNull();
        assertThat(allComplexityInserts.size()).isGreaterThan(0);
    }

//    @Test
//    void shouldShowComplexityScoreById() {
//        int complexityId = 1;
//
//        Complexity result = complexityRepo.showComplexityScoreById(complexityId);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getComplexityId()).isEqualTo(complexityId);
//        assertThat(result.getLabelType()).isEqualTo("Standard");
//        assertThat(result.getRateMultiplier()).isEqualTo(1.0);
//    }

//    @Test
//    void shouldCreateComplexityScore() {
//        Complexity complexityScore = new Complexity(7, "Advanced", 2.0);
//
//        complexityRepo.createComplexityScore(complexityScore);
//        List<Complexity> allComplexityScores = complexityRepo.showAllComplexityScores();
//
//        assertThat(allComplexityScores).hasSize(4);
//        assertThat(allComplexityScores).extracting(Complexity::getComplexityScore, Complexity::getLabelType, Complexity::getRateMultiplier)
//                .contains(tuple(7, "Advanced", 2.0));
//    }
}
