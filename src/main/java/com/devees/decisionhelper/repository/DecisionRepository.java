package com.devees.decisionhelper.repository;


import com.devees.decisionhelper.model.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {
    List<Decision> findByCategory(String category);
}