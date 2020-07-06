package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FinancialYear;

/**
 * Spring Data  repository for the FinancialYear entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {}
