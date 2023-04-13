package kr.ac.bokgpt.repository.welfare;

import kr.ac.bokgpt.domain.welfare.OfferWay;
import kr.ac.bokgpt.domain.welfare.WelfareOfferWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WelfareOfferWayRepository extends JpaRepository<WelfareOfferWay, Long> {
    @Query("select w.offerWay from WelfareOfferWay w where w.welfare.id = :welfareId order by w.offerWay.id")
    List<OfferWay> findAllOfferWaysByWelfareId(@Param("welfareId") Long welfareId);
}
