package kr.ac.bokgpt.repository.relationship.member;

import kr.ac.bokgpt.domain.classification.InterestTheme;
import kr.ac.bokgpt.domain.relationship.member.MemberInterestTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberInterestThemeRepository extends JpaRepository<MemberInterestTheme, Long> {
    @Query("select m.interestTheme from MemberInterestTheme m where m.member.id= :memberId order by m.interestTheme.id")
    InterestTheme findOneByMemberId(@Param("memberId") Long memberId);
}
