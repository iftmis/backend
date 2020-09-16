package org.tamisemi.iftmis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Risk;

/**
 * Spring Data  repository for the Risk entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskRepository extends JpaRepository<Risk, Long>, JpaSpecificationExecutor<Risk> {}
