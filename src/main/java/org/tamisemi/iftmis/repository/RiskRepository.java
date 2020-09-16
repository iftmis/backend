package org.tamisemi.iftmis.repository;

import java.util.List;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Risk;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Long>, JpaSpecificationExecutor<Risk> {
    @Query("FROM Risk r WHERE r.riskRegister.organisationUnit.id =:organisationUnitId AND r.riskRegister.id =:riskRegisterId")
    List<Risk> findAllByOrganisationIdAndRiskRegisterId(
        @Param("organisationUnitId") Long organisationUnitId,
        @Param("riskRegisterId") Long riskRegisterId
    );

    @Query("FROM Risk r WHERE r.riskRegister.organisationUnit.id =:organisationUnitId AND r.riskRegister.id =:riskRegisterId")
    Page<Risk> findAllByOrganisationIdAndRiskRegisterId(
        @Param("organisationUnitId") Long organisationUnitId,
        @Param("riskRegisterId") Long riskRegisterId,
        Pageable pageable
    );

    @Query(
        "FROM Risk r WHERE r.riskRegister.organisationUnit.id =:organisationUnitId AND r.riskRegister.financialYear.id =:financialYearId"
    )
    List<Risk> findAllByOrganisationIdAndFinancialYearId(
        @Param("organisationUnitId") Long organisationUnitId,
        @Param("financialYearId") Long financialYearId
    );

    @Query(
        "FROM Risk r WHERE r.riskRegister.organisationUnit.id =:organisationUnitId AND r.riskRegister.financialYear.id =:financialYearId"
    )
    Page<Risk> findAllByOrganisationIdAndFinancialYearId(
        @Param("organisationUnitId") Long organisationUnitId,
        @Param("financialYearId") Long financialYearId,
        Pageable pageable
    );
}
