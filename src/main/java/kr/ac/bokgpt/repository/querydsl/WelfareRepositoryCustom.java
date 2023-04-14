package kr.ac.bokgpt.repository.querydsl;

import kr.ac.bokgpt.dto.WelfareTitleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WelfareRepositoryCustom {
    Page<WelfareTitleDto> findWelfarePages(Pageable pageable);

    Page<WelfareTitleDto> findWelfarePagesByThemeId(Long interestThemeId, Pageable pageable);
}
