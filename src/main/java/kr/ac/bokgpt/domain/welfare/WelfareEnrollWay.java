package kr.ac.bokgpt.domain.welfare;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Welfare;
import lombok.*;

@Builder
@ToString(exclude = {"welfare", "enrollWay"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WelfareEnrollWay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_enroll_way_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enroll_way_ID")
    private EnrollWay enrollWay;
}
