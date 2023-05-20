package kr.ac.bokgpt.repository.querydsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.bokgpt.domain.Member;
import kr.ac.bokgpt.dto.MemberClassificationDto;
import kr.ac.bokgpt.dto.classification.LifeCycleDto;
import kr.ac.bokgpt.dto.classification.LocationDto;
import lombok.RequiredArgsConstructor;

import static kr.ac.bokgpt.domain.QMember.member;
import static kr.ac.bokgpt.domain.classification.QLifeCycle.lifeCycle;
import static kr.ac.bokgpt.domain.classification.QLocation.location;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Member findMemberByEmail(String email) {
        return queryFactory
                .selectFrom(member)
                .join(member.lifeCycle, lifeCycle).fetchJoin()
                .join(member.location, location).fetchJoin()
                .where(member.email.eq(email))
                .fetchOne();
    }

    @Override
    public MemberClassificationDto findClassificationByEmail(String email) {
        return queryFactory
                .select(Projections.constructor(
                        MemberClassificationDto.class
                        ,member.id
                        ,member.gender
                        ,Projections.constructor(
                                LifeCycleDto.class, lifeCycle.id, lifeCycle.lifeTime
                                )
                        ,Projections.constructor(
                                LocationDto.class, location.id, location.main, location.detail
                        )
                ))
                .from(member)
                .join(member.lifeCycle, lifeCycle)
                .join(member.location, location)
                .where(member.email.eq(email))
                .fetchOne();
    }
}
