package com.devees.decisionhelper.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecisionRequestDto {
    private String question;
    private String suggestion;
    private Long yesDecisionId;
    private Long noDecisionId;
    private String category;
}