package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.RiskRating;

/**
 * Spring Data  repository for the RiskRating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskRatingRepository extends JpaRepository<RiskRating, Long> {}
