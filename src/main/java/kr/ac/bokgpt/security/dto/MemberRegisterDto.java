package kr.ac.bokgpt.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.ac.bokgpt.domain.Gender;
import lombok.Getter;

public record MemberRegisterDto(
        String name,
        String email,
        String password,
        Gender gender,
        Long lifeCycleId,
        Long locationId
) {
}
