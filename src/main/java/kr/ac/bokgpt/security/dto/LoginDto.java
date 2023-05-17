package kr.ac.bokgpt.security.dto;

import lombok.Builder;

@Builder
public record LoginDto(
        String email,
        String password
) {
}
