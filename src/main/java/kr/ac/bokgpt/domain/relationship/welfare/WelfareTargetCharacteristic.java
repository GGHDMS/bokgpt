package kr.ac.bokgpt.domain.relationship.welfare;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.domain.classification.TargetCharacteristic;
import lombok.*;

@Builder
@ToString(exclude = {"welfare", "targetCharacteristic"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WelfareTargetCharacteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_target_characteristic_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_characteristic_id")
    private TargetCharacteristic targetCharacteristic;

    public void addWelfare(Welfare welfare) {
        this.welfare = welfare;
        welfare.getWelfareTargetCharacteristics().add(this);
    }
}
