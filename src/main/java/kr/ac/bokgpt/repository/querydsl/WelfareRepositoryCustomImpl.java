package kr.ac.bokgpt.repository.querydsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.bokgpt.dto.WelfareTitleDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.Order.ASC;
import static com.querydsl.core.types.Order.DESC;
import static kr.ac.bokgpt.domain.QWelfare.welfare;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareInterestTheme.welfareInterestTheme;

@AllArgsConstructor
public class WelfareRepositoryCustomImpl implements WelfareRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<WelfareTitleDto> findWelfarePages(Pageable pageable) {
        List<WelfareTitleDto> welfareTitleDtos = queryFactory
                .select(
                        Projections.constructor(WelfareTitleDto.class, welfare.id, welfare.serviceTitle, welfare.lastModifiedAt, welfare.view)
                )
                .from(welfare)
                .orderBy(getOrderSpecifier(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(welfare.count())
                .from(welfare);

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
