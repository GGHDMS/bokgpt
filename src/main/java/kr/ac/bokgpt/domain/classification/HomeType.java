package kr.ac.bokgpt.domain.classification;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HomeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_type_id")
    private Long id;

    private String type;
}
