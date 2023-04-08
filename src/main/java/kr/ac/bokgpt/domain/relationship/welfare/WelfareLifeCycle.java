package kr.ac.bokgpt.domain.relationship.welfare;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.domain.classification.LifeCycle;
import lombok.*;

@Builder
@ToString(exclude = {"welfare", "lifeCycle"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WelfareLifeCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_life_cycle_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "life_cycle_id")
    private LifeCycle lifeCycle;
}
