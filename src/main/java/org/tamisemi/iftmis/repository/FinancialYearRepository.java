package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FinancialYear;

import java.util.Optional;

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {
    Optional<FinancialYear> findFirstByIsOpenedTrue();

    @Modifying
    @Query("UPDATE FinancialYear f SET f.closed = true, f.isOpened = false WHERE f.isOpened = true")
    int closeCurrentFinancialYear();
}
