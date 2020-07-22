package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FindingResponse;

import java.util.List;

@Repository
public interface FindingResponseRepository extends JpaRepository<FindingResponse, Long> {
    List<FindingResponse> findAllByRecommendationId(@Param("recommendationId") Long recommendationId);

    Page<FindingResponse> findAllByRecommendationId(@Param("recommendationId") Long recommendationId, Pageable pageable);
}
