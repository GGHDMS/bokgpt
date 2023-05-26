package kr.ac.bokgpt.dto.commmunity.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CommentUpdateRequest(
        @NotNull
        String contents,
        Long parentCommentId

) { }

