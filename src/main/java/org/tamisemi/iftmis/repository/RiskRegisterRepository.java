package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.RiskRegister;

/**
 * Spring Data  repository for the RiskRegister entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskRegisterRepository extends JpaRepository<RiskRegister, Long> {}
