package kr.ac.bokgpt.domain.welfare;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.Welfare;
import lombok.*;

@Builder
@ToString(exclude = {"welfare", "offerWay"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class WelfareOfferWay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_offer_way_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_way_id")
    private OfferWay offerWay;
}

