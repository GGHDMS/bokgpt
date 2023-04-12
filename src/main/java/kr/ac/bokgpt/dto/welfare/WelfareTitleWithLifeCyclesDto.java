package kr.ac.bokgpt.dto.welfare;

import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record WelfareTitleWithLifeCyclesDto(
        Long id,
        String title,
        LocalDate lastModifiedAt,
        int view,
        List<LifeCycleDto> lifeCycles
) {
    public static WelfareTitleWithLifeCyclesDto from(WelfareTitleDto welfareTitleDto, List<LifeCycleDto> lifeCycles) {
        return WelfareTitleWithLifeCyclesDto.builder()
                .id(welfareTitleDto.id())
                .title(welfareTitleDto.title())
                .lastModifiedAt(welfareTitleDto.lastModifiedAt())
                .view(welfareTitleDto.view())
                .lifeCycles(lifeCycles)
                .build();
    }
}
