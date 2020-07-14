package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.RiskCategory;

/**
 * Spring Data  repository for the RiskCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskCategoryRepository extends JpaRepository<RiskCategory, Long> {}
