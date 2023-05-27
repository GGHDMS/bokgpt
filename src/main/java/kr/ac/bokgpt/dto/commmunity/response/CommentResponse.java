package kr.ac.bokgpt.dto.commmunity.response;

import kr.ac.bokgpt.dto.commmunity.CommentDto;
import kr.ac.bokgpt.dto.commmunity.MemberDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Builder
public record CommentResponse(
        Long id,
        String content,
        MemberDto memberDto,
        Long parentCommentId,
        Set<CommentResponse> childComments,
        LocalDateTime createdAt
) {
    public static CommentResponse from(CommentDto commentDto){
        Comparator<CommentResponse> childCommentComparator = Comparator // childComments 에 정렬된 set 이 들어가야 되기 때문에 사용했다. set 의 규칙을 정할 수 있다.
                .comparing(CommentResponse::createdAt) // 기본 오른차순, 내림하고 싶으면 .reversed
                .thenComparing(CommentResponse::id);

        return CommentResponse.builder()
                .id(commentDto.id())
                .parentCommentId(commentDto.parentCommentId())
                .memberDto(commentDto.memberDto())
                .content(commentDto.content())
                .childComments(new TreeSet<>(childCommentComparator))
                .createdAt(commentDto.createdAt())
                .build();
    }

    public boolean hasParentComment() {
        return parentCommentId() != null;
    }
}
