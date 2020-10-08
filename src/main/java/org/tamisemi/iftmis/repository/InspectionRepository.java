package org.tamisemi.iftmis.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.domain.enumeration.InspectionType;

/**
 * Spring Data  repository for the Inspection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    Page<Inspection> findByOrganisationUnit_Id(Long organisationUnitId, Pageable page);
    Page<Inspection> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDate startDate, LocalDate endDate, Pageable page);
    Page<Inspection> findAll(Pageable page);
    Page<Inspection> findAllByFinancialYear_IdAndOrganisationUnit_IdAndInspectionType(
        Long financialYear,
        Long organisationUnit,
        InspectionType inspectionType,
        Pageable page
    );
}
