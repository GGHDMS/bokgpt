package kr.ac.bokgpt.security.dto;

import kr.ac.bokgpt.domain.Gender;
import lombok.Builder;

@Builder
public record signupDto(
        String email,
        String password,
        String name,
        Gender gender,
        Long locationId,
        Long lifeCycleId
) {
}
