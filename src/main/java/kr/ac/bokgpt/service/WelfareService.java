package kr.ac.bokgpt.service;

import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.welfare.WelfareTitleDto;
import kr.ac.bokgpt.dto.welfare.WelfareTitleWithLifeCyclesDto;
import kr.ac.bokgpt.repository.LifeCycleRepository;
import kr.ac.bokgpt.repository.WelfareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class WelfareService {
    private final WelfareRepository welfareRepository;
    private final LifeCycleRepository lifeCycleRepository;

    public Page<WelfareTitleWithLifeCyclesDto> searchWelfareByInterestTheme(Long interestThemeId, Pageable pageable) {
        Page<WelfareTitleDto> welfareTitleDtos = welfareRepository.findThemePageByThemeId(interestThemeId, pageable);
        List<WelfareTitleDto> content = welfareTitleDtos.getContent();
        List<Long> welfareIds = content
                .stream()
                .map(WelfareTitleDto::id)
                .toList();
        Map<Long, List<LifeCycleDto>> lifecyclesMap = lifeCycleRepository.findLifeCycleByWelfareId(welfareIds);
        return new PageImpl<>(content.stream().map(
                        dto -> WelfareTitleWithLifeCyclesDto.from(dto, lifecyclesMap.get(dto.id()))
                )
                .toList(), welfareTitleDtos.getPageable(), welfareTitleDtos.getTotalElements());
    }
}
