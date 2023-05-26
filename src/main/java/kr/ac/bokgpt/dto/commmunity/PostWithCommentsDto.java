package kr.ac.bokgpt.dto.commmunity;


import kr.ac.bokgpt.domain.community.Post;
import kr.ac.bokgpt.dto.MemberDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record PostWithCommentsDto(
        Long id,
        MemberDto memberDto,
        String title,
        String content,

//        WelfareDto welfareId,
//        Long views,
        Set<CommentDto> comments,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy

) {
    public static PostWithCommentsDto from(Post post){
        return PostWithCommentsDto.builder()
                .id(post.getId())
                .memberDto(MemberDto.from(post.getMember()))
                .title(post.getTitle())
                .content(post.getContent())
//                .welfareId(WelfareDto.from(post.getWelfare()))
//                .views(post.getViews())
                .comments(post.getComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .createdBy(post.getCreatedBy())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .modifiedBy(post.getModifiedBy())
                .build();
    }
}
