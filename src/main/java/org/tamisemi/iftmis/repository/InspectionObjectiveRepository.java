package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionObjective;

/**
 * Spring Data  repository for the InspectionObjective entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionObjectiveRepository extends JpaRepository<InspectionObjective, Long> {}
