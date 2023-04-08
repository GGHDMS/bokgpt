package kr.ac.bokgpt.domain;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.classification.Location;
import kr.ac.bokgpt.domain.welfare.SupportCycle;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@ToString(exclude = {"location", "supportCycle"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Welfare {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welfare_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "support_cycle_id")
    private SupportCycle supportCycle;

    private String serviceId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String department;
    private String summary;
    private String supportedBy;
    private String selectionCriteria;
    private String serviceContent;
    private LocalDateTime lastModifiedAt;
    private String detailLink;
    private int view;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Welfare that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
