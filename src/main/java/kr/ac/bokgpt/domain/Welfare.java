package kr.ac.bokgpt.domain;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.classification.Location;
import kr.ac.bokgpt.domain.relationship.welfare.WelfareInterestTheme;
import kr.ac.bokgpt.domain.relationship.welfare.WelfareLifeCycle;
import kr.ac.bokgpt.domain.welfare.SupportCycle;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@ToString(exclude = {"location", "supportCycle", "welfareInterestThemes", "welfareLifeCycles"})
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

    @Builder.Default
    @OneToMany(mappedBy = "welfare")
    private List<WelfareInterestTheme> welfareInterestThemes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "welfare")
    private List<WelfareLifeCycle> welfareLifeCycles = new ArrayList<>();


    private String serviceTitle;
    private String serviceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String department;
    @Column(length = 1000)
    private String summary;
    @Column(length = 2000)
    private String supportedBy;
    @Column(length = 2000)
    private String selectionCriteria;
    @Column(length = 2000)
    private String serviceContent;
    private LocalDate lastModifiedAt;
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
