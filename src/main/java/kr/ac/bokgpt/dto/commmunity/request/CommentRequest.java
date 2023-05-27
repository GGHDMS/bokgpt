package kr.ac.bokgpt.dto.commmunity.request;

import jakarta.validation.constraints.NotNull;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.domain.community.Comment;
import kr.ac.bokgpt.domain.community.Post;
import lombok.Builder;

@Builder
public record CommentRequest(

        @NotNull String content,
        @NotNull Long postId,
        Long parentCommentId

) {

    public static CommentRequest of(String contents, Long postId, Long parentCommentId){
        return new CommentRequest(contents, postId,parentCommentId);
    }

    public Comment toEntity(Member member, Post post){
        return Comment.builder()
                .content(this.content)
                .post(post)
//                childComment가 꼭 필요한지??
                .parentCommentId(this.parentCommentId)
                .member(member)
                .build();
    }
}

