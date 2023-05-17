package kr.ac.bokgpt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.ac.bokgpt.domain.classification.LifeCycle;
import kr.ac.bokgpt.domain.classification.Location;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Builder
@ToString(exclude = {"lifeCycle", "location"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends AuditingFields{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "member_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "life_cycle_id")
    private LifeCycle lifeCycle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "email", unique = true)
    @NotNull
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name="gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @Column(name="password",length = 128)
    @NotNull
    @Size(max= 128)
    private String password;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

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

}
