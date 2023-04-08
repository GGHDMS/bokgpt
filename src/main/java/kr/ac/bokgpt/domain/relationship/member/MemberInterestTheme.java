package kr.ac.bokgpt.domain.relationship.member;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.classification.InterestTheme;
import lombok.*;

@Builder
@ToString(exclude = {"member", "interestTheme"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberInterestTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_interest_theme_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_theme_id")
    private InterestTheme interestTheme;
}
