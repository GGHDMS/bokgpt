package kr.ac.bokgpt.dto.commmunity;

import kr.ac.bokgpt.domain.Member;
import lombok.Builder;


@Builder
public record MemberDto(
        Long id,
        String nickname

) {
    public static MemberDto from(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .nickname(member.getName())
                .build();
    }
}
