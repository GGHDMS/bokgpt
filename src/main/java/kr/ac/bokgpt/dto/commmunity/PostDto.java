package kr.ac.bokgpt.dto.commmunity;

import kr.ac.bokgpt.domain.community.Post;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostDto(
        Long id,
        String title,
        String nickname,
//        Long views,
        LocalDateTime createdAt

) {

    public static PostDto from(Post post){
        String nickname = post.getMember().getName();
        if(nickname == null){
            nickname=post.getMember().getEmail();
        }

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .nickname(nickname)
                .createdAt(post.getCreatedAt())
//                .views(post.getViews())
                .build();
    }
}
