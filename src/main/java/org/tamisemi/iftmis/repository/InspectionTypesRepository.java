package org.tamisemi.iftmis.repository;

import org.tamisemi.iftmis.domain.InspectionTypes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InspectionTypes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionTypesRepository extends JpaRepository<InspectionTypes, Long>, JpaSpecificationExecutor<InspectionTypes> {
}
