package kr.ac.bokgpt.domain.community;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kr.ac.bokgpt.domain.AuditingFields;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.dto.commmunity.request.PostRequest;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@ToString(exclude = {"member", "comments"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends AuditingFields {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    @NotNull
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

//    private Long views;

    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<Comment> comments = new LinkedHashSet<>();

//    @OneToOne(mappedBy = "welfare_id")
//    private Welfare welfare;

    public void updatePost(PostRequest postRequest){
        this.title = postRequest.title();
        this.content = postRequest.contents();
    }


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
