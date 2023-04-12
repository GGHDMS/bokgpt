package kr.ac.bokgpt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@ToString(exclude = {"member", "welfare"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends AuditingFields{
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "welfare_id")
    private Welfare welfare;

    private String tile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
