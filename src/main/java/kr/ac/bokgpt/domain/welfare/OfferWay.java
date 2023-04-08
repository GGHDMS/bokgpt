package kr.ac.bokgpt.domain.welfare;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OfferWay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_way_id")
    private Long id;

    private String way;
}
