package kr.ac.bokgpt.dto.commmunity;

import kr.ac.bokgpt.domain.community.Comment;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommentDto(
        Long id,
        String content,
        MemberDto memberDto,
        Long parentCommentId,
        LocalDateTime createdAt
) {
    public static CommentDto from(Comment comment){
        return CommentDto.builder()
                .id(comment.getId())
                .parentCommentId(comment.getParentCommentId())
                .memberDto(MemberDto.from(comment.getMember()))
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
