package kr.ac.bokgpt.domain.classification;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LifeCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "life_cycle_id")
    private Long id;

    private String lifeTime;
}
