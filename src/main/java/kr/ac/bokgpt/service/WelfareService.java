package kr.ac.bokgpt.service;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.bokgpt.dto.WelfareAllInfoDto;
import kr.ac.bokgpt.dto.WelfareDto;
import kr.ac.bokgpt.dto.WelfareTitleDto;
import kr.ac.bokgpt.dto.WelfareTitleWithLifeCyclesDto;
import kr.ac.bokgpt.dto.classification.HomeTypeDto;
import kr.ac.bokgpt.dto.classification.InterestThemeDto;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.TargetCharacteristicDto;
import kr.ac.bokgpt.dto.welfare.EnrollWayDto;
import kr.ac.bokgpt.dto.welfare.OfferWayDto;
import kr.ac.bokgpt.repository.LifeCycleRepository;
import kr.ac.bokgpt.repository.WelfareRepository;
import kr.ac.bokgpt.repository.relationship.welfare.WelfareHomeTypeRepository;
import kr.ac.bokgpt.repository.relationship.welfare.WelfareInterestThemeRepository;
import kr.ac.bokgpt.repository.relationship.welfare.WelfareLifeCycleRepository;
import kr.ac.bokgpt.repository.relationship.welfare.WelfareTargetCharacteristicRepository;
import kr.ac.bokgpt.repository.welfare.WelfareEnrollWayRepository;
import kr.ac.bokgpt.repository.welfare.WelfareOfferWayRepository;
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
    private final WelfareHomeTypeRepository welfareHomeTypeRepository;
    private final WelfareInterestThemeRepository welfareInterestThemeRepository;
    private final WelfareLifeCycleRepository welfareLifeCycleRepository;
    private final WelfareTargetCharacteristicRepository welfareTargetCharacteristicRepository;
    private final WelfareOfferWayRepository welfareOfferWayRepository;
    private final WelfareEnrollWayRepository welfareEnrollWayRepository;

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

    public WelfareAllInfoDto searchWelfareDetail(Long welfareId) {
        WelfareDto welfareDto = welfareRepository.findByIdWithLocationAndSupportCycle(welfareId).map(WelfareDto::from)
                .orElseThrow(() -> new EntityNotFoundException("복지 정보가 없습니다 - welfareId: " + welfareId));

        List<HomeTypeDto> homeTypeDtos = welfareHomeTypeRepository.findAllHomeTypesByWelfareId(welfareId).stream().map(HomeTypeDto::from).toList();
        List<InterestThemeDto> interestThemeDtos = welfareInterestThemeRepository.findAllInterestThemesByWelfareId(welfareId).stream().map(InterestThemeDto::from).toList();
        List<LifeCycleDto> lifeCycleDtos = welfareLifeCycleRepository.findAllLifeCyclesByWelfareId(welfareId).stream().map(LifeCycleDto::from).toList();
        List<TargetCharacteristicDto> targetCharacteristicDtos = welfareTargetCharacteristicRepository.findAllTargetCharacteristicsByWelfareId(welfareId).stream().map(TargetCharacteristicDto::from).toList();
        List<OfferWayDto> offerWayDtos = welfareOfferWayRepository.findAllOfferWaysByWelfareId(welfareId).stream().map(OfferWayDto::from).toList();
        List<EnrollWayDto> enrollWayDtos = welfareEnrollWayRepository.findAllEnrollWaysByWelfareId(welfareId).stream().map(EnrollWayDto::from).toList();

        return WelfareAllInfoDto.from(welfareDto, homeTypeDtos, interestThemeDtos, lifeCycleDtos, targetCharacteristicDtos, offerWayDtos, enrollWayDtos);
    }
}
