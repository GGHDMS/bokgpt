package kr.ac.bokgpt.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LifeCycleWithWelfareIdDto;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.ac.bokgpt.domain.QWelfare.welfare;
import static kr.ac.bokgpt.domain.classification.QLifeCycle.lifeCycle;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareLifeCycle.welfareLifeCycle;

@AllArgsConstructor
public class LifeCycleRepositoryCustomImpl implements LifeCycleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Map<Long, List<LifeCycleDto>> findLifeCycleByWelfareId(List<Long> welfareId) {
        Map<Long, List<LifeCycleDto>> result = new HashMap<>();
        for (LifeCycleWithWelfareIdDto dto : queryFactory.select(
                        Projections.constructor(LifeCycleWithWelfareIdDto.class, lifeCycle.id, lifeCycle.lifeTime, welfare.id)
                )
                .from(welfareLifeCycle)
                .join(welfareLifeCycle.lifeCycle, lifeCycle)
                .where(welfareLifeCycle.welfare.id.in(welfareId))
                .fetch()) {

            Long welfareIdDto = dto.welfareId();
            if (result.containsKey(welfareIdDto)) {
                result.get(welfareIdDto).add(LifeCycleDto.from(dto));
            } else {
                List<LifeCycleDto> list = new ArrayList<>();
                list.add(LifeCycleDto.from(dto));
                result.put(welfareIdDto, list);
            }
        }
        return result;
    }
}
