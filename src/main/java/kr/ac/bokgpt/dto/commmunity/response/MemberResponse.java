package kr.ac.bokgpt.dto.commmunity.response;

import kr.ac.bokgpt.domain.Member;
import lombok.Builder;


@Builder
public record MemberResponse(
        Long id,
        String ninkname

) {
    public static MemberResponse from(Member member){
        return MemberResponse.builder()
                .id(member.getId())
                .ninkname(member.getName())
                .build();
    }
}
