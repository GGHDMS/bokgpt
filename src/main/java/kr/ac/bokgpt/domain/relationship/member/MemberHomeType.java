package kr.ac.bokgpt.domain.relationship.member;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.classification.HomeType;
import lombok.*;

@Builder
@ToString(exclude = {"member", "homeType"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberHomeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_home_type_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_type_id")
    private HomeType homeType;

}
