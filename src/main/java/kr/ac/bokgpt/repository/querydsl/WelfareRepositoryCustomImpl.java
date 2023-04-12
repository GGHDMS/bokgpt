package kr.ac.bokgpt.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.bokgpt.dto.welfare.WelfareTitleDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static kr.ac.bokgpt.domain.QWelfare.welfare;
import static kr.ac.bokgpt.domain.relationship.welfare.QWelfareInterestTheme.welfareInterestTheme;

@AllArgsConstructor
public class WelfareRepositoryCustomImpl implements WelfareRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<WelfareTitleDto> findThemePageByThemeId(Long interestThemeId, Pageable pageable) {
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
                .select(welfareInterestTheme.count())
                .from(welfareInterestTheme)
                .join(welfareInterestTheme.welfare, welfare)
                .where(welfareInterestTheme.interestTheme.id.eq(interestThemeId));

        return PageableExecutionUtils.getPage(welfareTitleDtos, pageable, countQuery::fetchOne);
    }
}
