package kr.ac.bokgpt.domain.relationship.welfare;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.domain.classification.HomeType;
import lombok.*;

@Builder
@ToString(exclude = {"welfare", "homeType"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WelfareHomeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_home_type_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_type_id")
    private HomeType homeType;

}
