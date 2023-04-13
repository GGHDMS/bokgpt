package kr.ac.bokgpt.repository.relationship.welfare;

import kr.ac.bokgpt.domain.classification.InterestTheme;
import kr.ac.bokgpt.domain.relationship.welfare.WelfareInterestTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WelfareInterestThemeRepository extends JpaRepository<WelfareInterestTheme, Long> {
    @Query("select w.interestTheme from WelfareInterestTheme w where w.welfare.id = :welfareId order by w.interestTheme.id")
    List<InterestTheme> findAllInterestThemesByWelfareId(@Param("welfareId") Long welfareId);
}
