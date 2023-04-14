package kr.ac.bokgpt.dto;

import kr.ac.bokgpt.domain.Welfare;
import kr.ac.bokgpt.dto.classification.LocationDto;
import kr.ac.bokgpt.dto.welfare.SupportCycleDto;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WelfareDto(
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
        SupportCycleDto supportCycle
) {
    public static WelfareDto from(Welfare entity) {
        return WelfareDto.builder()
                .id(entity.getId())
                .serviceId(entity.getServiceId())
                .serviceTitle(entity.getServiceTitle())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .department(entity.getDepartment())
                .summary(entity.getSummary())
                .supportedBy(entity.getSupportedBy())
                .selectionCriteria(entity.getSelectionCriteria())
                .serviceContent(entity.getServiceContent())
                .lastModifiedAt(entity.getLastModifiedAt())
                .detailLink(entity.getDetailLink())
                .view(entity.getView())
                .location(LocationDto.from(entity.getLocation()))
                .supportCycle(SupportCycleDto.from(entity.getSupportCycle()))
                .build();
    }
}
