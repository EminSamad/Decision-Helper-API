package com.devees.decisionhelper.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "decisions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String question;


    @Column(nullable = false)
    private String suggestion;


    private Long yesDecisionId;
    private Long noDecisionId;


    @Builder.Default
    private long yesCount = 0;
    @Builder.Default
    private long noCount = 0;


    private String category;


    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}