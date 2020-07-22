package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FindingRecommendation;

import java.util.List;

/**
 * Spring Data  repository for the FindingRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindingRecommendationRepository extends JpaRepository<FindingRecommendation, Long> {
    List<FindingRecommendation> findAllByFindingId(@Param("findingId") Long findingId);

    Page<FindingRecommendation> findAllByFindingId(@Param("findingId") Long findingId, Pageable pageable);
}
