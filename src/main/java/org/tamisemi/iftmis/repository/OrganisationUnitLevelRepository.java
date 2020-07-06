package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;

/**
 * Spring Data  repository for the OrganisationUnitLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganisationUnitLevelRepository extends JpaRepository<OrganisationUnitLevel, Long> {}
