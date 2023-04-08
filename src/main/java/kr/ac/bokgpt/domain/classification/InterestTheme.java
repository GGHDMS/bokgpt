package kr.ac.bokgpt.domain.classification;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class InterestTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_theme_id")
    private Long id;

    private String theme;
}
