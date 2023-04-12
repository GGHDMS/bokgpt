package kr.ac.bokgpt.repository.querydsl;

import kr.ac.bokgpt.dto.welfare.WelfareTitleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WelfareRepositoryCustom {
    Page<WelfareTitleDto> findThemePageByThemeId(Long interestThemeId, Pageable pageable);
}
