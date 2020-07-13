package org.tamisemi.iftmis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.OrganisationUnit;

import java.util.List;

@Repository
public interface OrganisationUnitRepository extends JpaRepository<OrganisationUnit, Long> {
    @Query("FROM OrganisationUnit i WHERE (lower(i.name) LIKE %:query% OR lower(i.code) LIKE %:query%) ORDER BY i.name ASC")
    List<OrganisationUnit> findAll(@Param("query") String query);

    @Query("FROM OrganisationUnit i WHERE (lower(i.name) LIKE %:query% OR lower(i.code) LIKE %:query%) ORDER BY i.name ASC")
    Page<OrganisationUnit> findAll(@Param("query") String query, Pageable pageable);
}
