package org.tamisemi.iftmis.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.RiskRegister;

@Repository
public interface RiskRegisterRepository extends JpaRepository<RiskRegister, Long> {
    List<RiskRegister> findAllByFinancialYearId(Long financialYearId);

    List<RiskRegister> findAllByOrganisationUnitId(Long organisationUnitId);

    List<RiskRegister> findAllByFinancialYearIdAndOrganisationUnitId(Long financialYearId, Long organisationUnitId);

    Page<RiskRegister> findAllByOrganisationUnitId(Long organisationUnitId, Pageable pageable);

    Page<RiskRegister> findAllByFinancialYearId(Long financialYearId, Pageable pageable);

    Page<RiskRegister> findAllByFinancialYearIdAndOrganisationUnitId(Long financialYearId, Long organisationUnitId, Pageable pageable);
}
