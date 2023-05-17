package kr.ac.bokgpt.security.dto;

import lombok.*;

@Builder
public record TokenDto(
        String token
) {
}
