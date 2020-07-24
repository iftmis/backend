package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionBudget;

/**
 * Spring Data  repository for the InspectionBudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionBudgetRepository extends JpaRepository<InspectionBudget, Long> {}
