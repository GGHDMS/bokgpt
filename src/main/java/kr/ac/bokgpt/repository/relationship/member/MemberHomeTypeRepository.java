package kr.ac.bokgpt.repository.relationship.member;

import kr.ac.bokgpt.domain.classification.HomeType;
import kr.ac.bokgpt.domain.relationship.member.MemberHomeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberHomeTypeRepository extends JpaRepository<MemberHomeType, Long> {
    MemberHomeType findOneByMember_Id(Long memberId);

    @Query("select m.homeType from MemberHomeType m where m.member.id= :memberId order by m.homeType.id")
    HomeType findHomeTypeByMemberId(@Param("memberId") Long memberId);
}
