package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.InspectionProcedure;

/**
 * Spring Data  repository for the InspectionProcedure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionProcedureRepository extends JpaRepository<InspectionProcedure, Long> {}
