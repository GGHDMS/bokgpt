package kr.ac.bokgpt.repository.querydsl;

import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.dto.WelfareTitleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WelfareRepositoryCustom {
    Page<WelfareTitleDto> findWelfaresByClassifications(
            Gender gender,
            Long lifeCycleId,
            Long locationId,
            Long homeTypeId,
            Long interestThemeId,
            Pageable pageable
    );
    Page<WelfareTitleDto> findWelfarePagesByThemeId(Long interestThemeId, Pageable pageable);
}
