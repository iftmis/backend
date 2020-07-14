package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.RiskRank;

/**
 * Spring Data  repository for the RiskRank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskRankRepository extends JpaRepository<RiskRank, Long> {}
