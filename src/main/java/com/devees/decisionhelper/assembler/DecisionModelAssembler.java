package com.devees.decisionhelper.assembler;


import com.devees.decisionhelper.controller.DecisionController;
import com.devees.decisionhelper.model.Decision;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class DecisionModelAssembler implements RepresentationModelAssembler<Decision, EntityModel<Decision>> {
    @Override
    public EntityModel<Decision> toModel(Decision decision) {
        EntityModel<Decision> model = EntityModel.of(decision,
                linkTo(methodOn(DecisionController.class).getDecision(decision.getId())).withSelfRel(),
                linkTo(methodOn(DecisionController.class).answer(decision.getId(), "yes")).withRel("yes"),
                linkTo(methodOn(DecisionController.class).answer(decision.getId(), "no")).withRel("no"),
                linkTo(methodOn(DecisionController.class).random()).withRel("random")
        );


        if (decision.getYesDecisionId() != null)
            model.add(linkTo(methodOn(DecisionController.class).getDecision(decision.getYesDecisionId())).withRel("yes-target"));
        if (decision.getNoDecisionId() != null)
            model.add(linkTo(methodOn(DecisionController.class).getDecision(decision.getNoDecisionId())).withRel("no-target"));


        return model;
    }
}