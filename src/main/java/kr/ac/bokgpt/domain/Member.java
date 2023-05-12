package kr.ac.bokgpt.domain;

import jakarta.persistence.*;
import kr.ac.bokgpt.domain.classification.LifeCycle;
import kr.ac.bokgpt.domain.classification.Location;
import kr.ac.bokgpt.security.domain.Role;
import lombok.*;

import java.util.Objects;

@Builder
@ToString(exclude = {"lifeCycle", "location"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "life_cycle_id")
    private LifeCycle lifeCycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name="provider")
    private String provider;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    public Member update(String name){
        this.name= name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
