package kr.ac.bokgpt.dto.commmunity.response;

import kr.ac.bokgpt.domain.community.Comment;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentResponseDto(
        Long id,
        String contents,
        MemberResponse memberResponse,
        Long parentCommentId,
        LocalDateTime createdAt
) {
    public static CommentResponseDto from(Comment comment){
        return CommentResponseDto.builder()
                .id(comment.getId())
                .parentCommentId(comment.getParentCommentId())
                .memberResponse(MemberResponse.from(comment.getMember()))
                .contents(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
