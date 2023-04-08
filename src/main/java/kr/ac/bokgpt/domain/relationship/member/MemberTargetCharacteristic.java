package kr.ac.bokgpt.domain.relationship.member;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.classification.TargetCharacteristic;
import lombok.*;

@Builder
@ToString(exclude = {"member", "targetCharacteristic"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberTargetCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_target_characteristic_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_characteristic_id")
    private TargetCharacteristic targetCharacteristic;
}
