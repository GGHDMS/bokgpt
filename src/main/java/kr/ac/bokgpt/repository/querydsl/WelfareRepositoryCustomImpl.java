package kr.ac.bokgpt.repository.querydsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.bokgpt.domain.Gender;
import kr.ac.bokgpt.dto.WelfareTitleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.Order.ASC;
import static com.querydsl.core.types.Order.DESC;
import static kr.ac.bokgpt.domain.QWelfare.welfare;
import static kr.ac.bokgpt.domain.classification.QLocation.location;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareHomeType.welfareHomeType;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareInterestTheme.welfareInterestTheme;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareLifeCycle.welfareLifeCycle;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareTargetCharacteristic.welfareTargetCharacteristic;

@RequiredArgsConstructor
public class WelfareRepositoryCustomImpl implements WelfareRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<WelfareTitleDto> findWelfaresByClassifications(Gender gender, Long lifeCycleId, Long locationId, Long homeTypeId, Long interestThemeId, Pageable pageable) {
        JPAQuery<WelfareTitleDto> query = queryFactory
                .select(
                        Projections.constructor(WelfareTitleDto.class, welfare.id, welfare.serviceTitle, welfare.lastModifiedAt, welfare.view)
                )
                .from(welfare)
                .distinct();

        JPAQuery<Long> countQuery = queryFactory
                .select(welfare.id.countDistinct())
                .from(welfare);

        if (lifeCycleId != 0) {
            query
                    .join(welfare.welfareLifeCycles, welfareLifeCycle)
                    .on(welfareLifeCycle.lifeCycle.id.in(lifeCycleId, 8L));

            countQuery
                    .join(welfare.welfareLifeCycles, welfareLifeCycle)
                    .on(welfareLifeCycle.lifeCycle.id.in(lifeCycleId, 8L));
        }

        if (locationId != 0) {
            BooleanExpression locationCondition = location.id.eq(locationId)
                    .and(locationId != 1 ? location.id.eq(1L) : Expressions.TRUE);

            query
                    .join(welfare.location, location)
                    .on(locationCondition);

            countQuery
                    .join(welfare.location, location)
                    .on(locationCondition);
        }

        if (homeTypeId != 0) {
            query
                    .join(welfare.welfareHomeTypes, welfareHomeType)
                    .on(welfareHomeType.homeType.id.in(homeTypeId, 7L));

            countQuery
                    .join(welfare.welfareHomeTypes, welfareHomeType)
                    .on(welfareHomeType.homeType.id.in(homeTypeId, 7L));
        }

        BooleanExpression targetCharacteristicCondition = null;

        if (gender == Gender.FEMALE) {
            targetCharacteristicCondition = welfareTargetCharacteristic.targetCharacteristic.id.in(7L, 8L, 11L);
        } else if (gender == Gender.MALE) {
            targetCharacteristicCondition = welfareTargetCharacteristic.targetCharacteristic.id.notIn(7L, 8L, 11L);
        }

        if (targetCharacteristicCondition != null) {
            query
                    .join(welfare.welfareTargetCharacteristics, welfareTargetCharacteristic)
                    .on(targetCharacteristicCondition);

            countQuery
                    .join(welfare.welfareTargetCharacteristics, welfareTargetCharacteristic)
                    .on(targetCharacteristicCondition);
        }

        if (interestThemeId != 0) {
            query
                    .join(welfare.welfareInterestThemes, welfareInterestTheme)
                    .on(welfareInterestTheme.interestTheme.id.eq(interestThemeId));

            countQuery
                    .join(welfare.welfareInterestThemes, welfareInterestTheme)
                    .on(welfareInterestTheme.interestTheme.id.eq(interestThemeId));
        }

        List<WelfareTitleDto> welfareTitleDtos = query
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(welfareTitleDtos, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<WelfareTitleDto> findWelfarePagesByThemeId(Long interestThemeId, Pageable pageable) {
        List<WelfareTitleDto> welfareTitleDtos = queryFactory
                .select(
                        Projections.constructor(WelfareTitleDto.class, welfare.id, welfare.serviceTitle, welfare.lastModifiedAt, welfare.view)
                )
                .from(welfare)
                .join(welfare.welfareInterestThemes, welfareInterestTheme)
                .where(welfareInterestTheme.interestTheme.id.eq(interestThemeId))
                .orderBy(welfare.lastModifiedAt.desc(), welfare.view.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(welfare.count())
                .from(welfare)
                .join(welfare.welfareInterestThemes, welfareInterestTheme)
                .where(welfareInterestTheme.interestTheme.id.eq(interestThemeId));

        return PageableExecutionUtils.getPage(welfareTitleDtos, pageable, countQuery::fetchOne);
    }


    private OrderSpecifier<?>[] getOrderSpecifier(Sort sort) {
        if (sort == null || sort.isUnsorted()) {
            return new OrderSpecifier<?>[]{new OrderSpecifier<>(DESC, welfare.lastModifiedAt), new OrderSpecifier<>(DESC, welfare.view)};
        }
        List<OrderSpecifier<?>> orders = new ArrayList<>();
        sort.forEach(order -> {
            if ("view".equals(order.getProperty())) {
                OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(order.isAscending() ? ASC : DESC, welfare.view);
                orders.add(orderSpecifier);
            } else if ("lastModifiedAt".equals(order.getProperty())) {
                OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(order.isAscending() ? ASC : DESC, welfare.lastModifiedAt);
                orders.add(orderSpecifier);
            } else {
                OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(order.isAscending() ? ASC : DESC, welfare.serviceTitle);
                orders.add(orderSpecifier);
            }
        });

        return orders.toArray(new OrderSpecifier[0]);
    }
}
