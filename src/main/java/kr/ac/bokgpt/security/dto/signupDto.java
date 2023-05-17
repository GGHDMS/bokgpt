package kr.ac.bokgpt.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ac.bokgpt.domain.Gender;
import lombok.Builder;

@Builder
public record signupDto(
        @NotBlank(message = "email 은 blank 일 수 없습니다!")
        String email,
        @NotBlank(message = "password 는 blank 일 수 없습니다!")
        String password,
        @NotBlank(message = "name 은 blank 일 수 없습니다!")
        String name,
        @NotNull(message = "gender 는 null 일 수 없습니다!")
        Gender gender,
        Long locationId,
        Long lifeCycleId,
        Long homeTypeId
) {
}
