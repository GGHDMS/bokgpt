package kr.ac.bokgpt.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ac.bokgpt.domain.Gender;
import lombok.Builder;

@Builder
public record MemberRequest(
        @NotBlank(message = "name 은 blank 일 수 없습니다!")
        String name,
        @NotNull(message = "gender 는 null 일 수 없습니다!")
        Gender gender,
        Long locationId,
        Long lifeCycleId,
        Long homeTypeId
) {
}
