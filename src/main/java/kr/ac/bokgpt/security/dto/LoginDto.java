package kr.ac.bokgpt.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginDto(
        @NotBlank(message = "email은 blank 일 수 없습니다!")
        String email,
        @NotBlank(message = "password는 blank 일 수 없습니다!")
        String password
) {
}
