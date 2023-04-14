package kr.ac.bokgpt.service;

import kr.ac.bokgpt.dto.classification.*;
import kr.ac.bokgpt.dto.welfare.EnrollWayDto;
import kr.ac.bokgpt.dto.welfare.OfferWayDto;
import kr.ac.bokgpt.dto.welfare.SupportCycleDto;
import kr.ac.bokgpt.repository.classification.*;
import kr.ac.bokgpt.repository.welfare.EnrollWayRepository;
import kr.ac.bokgpt.repository.welfare.OfferWayRepository;
import kr.ac.bokgpt.repository.welfare.SupportCycleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TableInfoService {
    private final HomeTypeRepository homeTypeRepository;
    private final InterestThemeRepository interestThemeRepository;
    private final LifeCycleRepository lifeCycleRepository;
    private final LocationRepository locationRepository;
    private final TargetCharacteristicRepository targetCharacteristicRepository;
    private final EnrollWayRepository enrollWayRepository;
    private final OfferWayRepository offerWayRepository;
    private final SupportCycleRepository supportCycleRepository;

    public List<HomeTypeDto> allHomeTypes() {
        return homeTypeRepository.findAll().stream().map(HomeTypeDto::from).toList();
    }

    public List<InterestThemeDto> allInterestThemes() {
        return interestThemeRepository.findAll().stream().map(InterestThemeDto::from).toList();
    }

    public List<LifeCycleDto> allLifeCycles() {
        return lifeCycleRepository.findAll().stream().map(LifeCycleDto::from).toList();
    }

    public List<LocationDto> allLocations() {
        return locationRepository.findAll().stream().map(LocationDto::from).toList();
    }

    public List<TargetCharacteristicDto> allTargetCharacteristics() {
        return targetCharacteristicRepository.findAll().stream().map(TargetCharacteristicDto::from).toList();
    }

    public List<EnrollWayDto> allEnrollWays() {
        return enrollWayRepository.findAll().stream().map(EnrollWayDto::from).toList();
    }

    public List<OfferWayDto> allOfferWays() {
        return offerWayRepository.findAll().stream().map(OfferWayDto::from).toList();
    }

    public List<SupportCycleDto> allSupportCycles() {
        return supportCycleRepository.findAll().stream().map(SupportCycleDto::from).toList();
    }
}
