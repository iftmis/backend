package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FindingRecommendation;

/**
 * Spring Data  repository for the FindingRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindingRecommendationRepository extends JpaRepository<FindingRecommendation, Long> {}
