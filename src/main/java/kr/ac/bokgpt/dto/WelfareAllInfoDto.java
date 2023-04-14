package kr.ac.bokgpt.dto;

import kr.ac.bokgpt.dto.classification.*;
import kr.ac.bokgpt.dto.welfare.EnrollWayDto;
import kr.ac.bokgpt.dto.welfare.OfferWayDto;
import kr.ac.bokgpt.dto.welfare.SupportCycleDto;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record WelfareAllInfoDto(
        Long id,
        String serviceId,
        String serviceTitle,
        LocalDate startDate,
        LocalDate endDate,
        String department,
        String summary,
        String supportedBy,
        String selectionCriteria,
        String serviceContent,
        LocalDate lastModifiedAt,
        String detailLink,
        int view,
        LocationDto location,
        SupportCycleDto supportCycle,
        List<HomeTypeDto> homeTypes,
        List<InterestThemeDto> interestThemes,
        List<LifeCycleDto> lifeCycles,
        List<TargetCharacteristicDto> targetCharacteristics,
        List<OfferWayDto> offerWays,
        List<EnrollWayDto> enrollWays
) {
    public static WelfareAllInfoDto from(
            WelfareDto dto,
            List<HomeTypeDto> homeTypes,
            List<InterestThemeDto> interestThemes,
            List<LifeCycleDto> lifeCycles,
            List<TargetCharacteristicDto> targetCharacteristics,
            List<OfferWayDto> offerWays,
            List<EnrollWayDto> enrollWays
    ) {
        return WelfareAllInfoDto.builder()
                .id(dto.id())
                .serviceId(dto.serviceId())
                .serviceTitle(dto.serviceTitle())
                .startDate(dto.startDate())
                .endDate(dto.endDate())
                .department(dto.department())
                .summary(dto.summary())
                .supportedBy(dto.supportedBy())
                .selectionCriteria(dto.selectionCriteria())
                .serviceContent(dto.serviceContent())
                .lastModifiedAt(dto.lastModifiedAt())
                .detailLink(dto.detailLink())
                .view(dto.view())
                .location(dto.location())
                .supportCycle(dto.supportCycle())
                .homeTypes(homeTypes)
                .interestThemes(interestThemes)
                .lifeCycles(lifeCycles)
                .targetCharacteristics(targetCharacteristics)
                .offerWays(offerWays)
                .enrollWays(enrollWays)
                .build();
    }
}
