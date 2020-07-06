package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionRecommendation;

/**
 * Spring Data  repository for the InspectionRecommendation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionRecommendationRepository extends JpaRepository<InspectionRecommendation, Long> {}
