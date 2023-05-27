package kr.ac.bokgpt.dto.commmunity.request;

import jakarta.validation.constraints.NotNull;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.community.Post;


public record PostRequest(
        @NotNull
        String title,
        @NotNull
        String content
) {

    public static PostRequest of(String title, String contents){
        return new PostRequest(title,contents);
    }

    public Post toEntity(Member member){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .member(member)
                .build();
    }
}
