package kr.ac.bokgpt.domain.relationship.welfare;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.domain.classification.InterestTheme;
import lombok.*;

@Builder
@ToString(exclude = {"welfare", "interestTheme"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WelfareInterestTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_interest_theme_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_theme_id")
    private InterestTheme interestTheme;

    public void addWelfare(Welfare welfare) {
        this.welfare = welfare;
        welfare.getWelfareInterestThemes().add(this);
    }
}
