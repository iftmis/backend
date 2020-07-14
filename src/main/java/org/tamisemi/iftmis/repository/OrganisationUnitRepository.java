package org.tamisemi.iftmis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.OrganisationUnit;

/**
 * Spring Data  repository for the OrganisationUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganisationUnitRepository extends JpaRepository<OrganisationUnit, Long> {
    List<OrganisationUnit> findByParent_Id(Long parent_id);
}
