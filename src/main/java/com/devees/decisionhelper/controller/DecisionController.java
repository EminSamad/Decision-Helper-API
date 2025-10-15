package com.devees.decisionhelper.controller;


import com.devees.decisionhelper.assembler.DecisionModelAssembler;
import com.devees.decisionhelper.dto.DecisionRequestDto;
import com.devees.decisionhelper.model.Decision;
import com.devees.decisionhelper.service.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/decisions")
@RequiredArgsConstructor
public class DecisionController {
    private final DecisionService svc;
    private final DecisionModelAssembler assembler;


    @PostMapping
    public ResponseEntity<EntityModel<Decision>> create(@RequestBody DecisionRequestDto dto) {
        Decision d = svc.create(dto);
        EntityModel<Decision> model = assembler.toModel(d);
        return ResponseEntity.created(URI.create("/decisions/" + d.getId())).body(model);
    }


    @GetMapping
    public CollectionModel<EntityModel<Decision>> all() {
        List<EntityModel<Decision>> list = svc.getAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(list);
    }


    @GetMapping("/{id}")
    public EntityModel<Decision> getDecision(@PathVariable Long id) {
        return assembler.toModel(svc.getById(id));
    }


    @PostMapping("/{id}/answer")
    public EntityModel<Decision> answer(@PathVariable Long id, @RequestParam String value) {
        boolean yes = "yes".equalsIgnoreCase(value);
        Decision d = svc.answer(id, yes);
        return assembler.toModel(d);
    }


    @GetMapping("/random")
    public ResponseEntity<EntityModel<Decision>> random() {
        return svc.getRandom().map(dec -> ResponseEntity.ok(assembler.toModel(dec))).orElseGet(() -> ResponseEntity.noContent().build());
    }
}