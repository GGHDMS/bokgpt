package kr.ac.bokgpt.dto.commmunity.response;


import kr.ac.bokgpt.dto.MemberDto;
import kr.ac.bokgpt.dto.commmunity.CommentDto;
import kr.ac.bokgpt.dto.commmunity.PostWithCommentsDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder
public record PostWithCommentsResponse(
        Long id,
        MemberDto member,
        String title,
        String content,

//        WelfareDto welfareId,
//        Long views,
        Set<CommentResponse> comments,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy

) {
    public static PostWithCommentsResponse from(PostWithCommentsDto dto){
        return PostWithCommentsResponse.builder()
                .id(dto.id())
                .member(dto.memberDto())
                .title(dto.title())
                .content(dto.content())
//                .welfareId(WelfareDto.from(post.getWelfare()))
//                .views(post.getViews())
                .comments(organizeChildComments(dto.comments()))
                .createdBy(dto.createdBy())
                .createdAt(dto.createdAt())
                .modifiedAt(dto.modifiedAt())
                .modifiedBy(dto.modifiedBy())
                .build();
    }

    private static Set<CommentResponse> organizeChildComments(Set<CommentDto> dtos) { // db는 댓글과, 대댓글 이 하나의 테이블에 있다 그렇기 때문에 계층적 구조로 바꿔줘야함 우린 dto 가 계층을 바꾸게 할거임, service 내부에서 로직을 구현하는 방법도 있다.
        Map<Long, CommentResponse> map = dtos.stream()
                .map(CommentResponse::from)
                .collect(Collectors.toMap(CommentResponse::id, Function.identity())); // id로 접근 할 수 있게 mapping 해준다.

        map.values().stream()
                .filter(CommentResponse::hasParentComment) // 자식 댓글만 가져온다.
                .forEach(comment -> {
                    CommentResponse parentComment = map.get(comment.parentCommentId()); // 자식 comment 의 부모 댓글 id 를 가져온다.
                    parentComment.childComments().add(comment);
                });

        return map.values().stream()
                .filter(comment -> !comment.hasParentComment()) // 부모 comment 인가?
                .collect(Collectors.toCollection(() ->
                        new TreeSet<>(Comparator
                                .comparing(CommentResponse::createdAt) // 부모 댓글들을 정렬
                                .reversed() // 내림차순 정렬을 하기 위해서
                                .thenComparing(CommentResponse::id) // 오름차순 정렬
                        )
                ));
    }
}
