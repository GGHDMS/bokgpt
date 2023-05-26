package kr.ac.bokgpt.dto.commmunity.response;


import kr.ac.bokgpt.domain.community.Post;
import kr.ac.bokgpt.dto.MemberDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record PostWithCommentsResponseDto(
        Long id,
        MemberDto memberId,
        String title,

//        WelfareDto welfareId,
//        Long views,
        Set<CommentResponseDto> comments,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy

) {
    public static PostWithCommentsResponseDto from(Post post){
        return PostWithCommentsResponseDto.builder()
                .id(post.getId())
                .memberId(MemberDto.from(post.getMember()))
                .title(post.getTitle())
//                .welfareId(WelfareDto.from(post.getWelfare()))
//                .views(post.getViews())
                .comments(post.getComments().stream().map(CommentResponseDto::from).collect(Collectors.toSet()))
                .createdBy(post.getCreatedBy())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .modifiedBy(post.getModifiedBy())
                .build();
    }
}
