package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionPlan;

/**
 * Spring Data  repository for the InspectionPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionPlanRepository extends JpaRepository<InspectionPlan, Long> {}
