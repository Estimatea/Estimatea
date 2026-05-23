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
}
