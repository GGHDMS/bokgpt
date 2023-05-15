package kr.ac.bokgpt.repository.querydsl;

import kr.ac.bokgpt.dto.classification.LifeCycleDto;

import java.util.List;
import java.util.Map;

public interface LifeCycleRepositoryCustom {
    Map<Long, List<LifeCycleDto>> findLifeCycleByWelfareId(List<Long> welfareId);
}
