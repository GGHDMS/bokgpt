package kr.ac.bokgpt.repository.relationship.welfare;

import kr.ac.bokgpt.domain.classification.LifeCycle;
import kr.ac.bokgpt.domain.relationship.welfare.WelfareLifeCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WelfareLifeCycleRepository extends JpaRepository<WelfareLifeCycle, Long> {
    @Query("select w.lifeCycle from WelfareLifeCycle w where w.welfare.id = :welfareId order by w.lifeCycle.id")
    List<LifeCycle> findAllLifeCyclesByWelfareId(@Param("welfareId") Long welfareId);
}
