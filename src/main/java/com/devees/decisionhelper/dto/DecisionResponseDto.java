package com.devees.decisionhelper.dto;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecisionResponseDto {
    private Long id;
    private String question;
    private String suggestion;
    private Long yesDecisionId;
    private Long noDecisionId;
    private long yesCount;
    private long noCount;
    private String category;
}