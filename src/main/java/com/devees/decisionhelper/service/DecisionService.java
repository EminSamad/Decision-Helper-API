package com.devees.decisionhelper.service;


import com.devees.decisionhelper.dto.DecisionRequestDto;
import com.devees.decisionhelper.exception.NotFoundException;
import com.devees.decisionhelper.model.Decision;
import com.devees.decisionhelper.repository.DecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DecisionService {
    private final DecisionRepository repo;


    public Decision create(DecisionRequestDto dto) {
        Decision d = Decision.builder()
                .question(dto.getQuestion())
                .suggestion(dto.getSuggestion())
                .yesDecisionId(dto.getYesDecisionId())
                .noDecisionId(dto.getNoDecisionId())
                .category(dto.getCategory())
                .build();
        return repo.save(d);
    }


    public Decision getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Decision not found: " + id));
    }


    public List<Decision> getAll() { return repo.findAll(); }


    @Transactional
    public Decision answer(Long id, boolean yes) {
        Decision d = getById(id);
        if (yes) d.setYesCount(d.getYesCount() + 1);
        else d.setNoCount(d.getNoCount() + 1);
        return repo.save(d);
    }


    public Optional<Decision> getRandom() {
        List<Decision> all = repo.findAll();
        if (all.isEmpty()) return Optional.empty();
        int idx = (int) (Math.random() * all.size());
        return Optional.of(all.get(idx));
    }
}