package kr.ac.bokgpt.dto.welfare;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WelfareTitleDto(
        Long id,
        String title,
        LocalDate lastModifiedAt,
        int view
) {
}
