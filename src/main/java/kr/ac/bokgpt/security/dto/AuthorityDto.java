package kr.ac.bokgpt.security.dto;

import kr.ac.bokgpt.domain.Authority;
import lombok.*;

@Builder
public record AuthorityDto(
        String authorityName
) {

    public static AuthorityDto from(Authority authority) {
        return AuthorityDto.builder()
                .authorityName(authority.getAuthorityName())
                .build();
    }
}