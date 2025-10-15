package com.devees.decisionhelper.config;

import com.devees.decisionhelper.model.Decision;
import com.devees.decisionhelper.repository.DecisionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(DecisionRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Decision d1 = Decision.builder()
                        .question("Get a coffee now?")
                        .suggestion("Take a short coffee break.")
                        .category("Daily")
                        .build();
                d1 = repo.save(d1);

                Decision d2 = Decision.builder()
                        .question("Work on project A?")
                        .suggestion("Focus 1 hour on project A.")
                        .category("Work")
                        .build();
                d2 = repo.save(d2);

                d1.setYesDecisionId(d2.getId());
                repo.save(d1);
            }
        };
    }
}
