package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Procedure;

/**
 * Spring Data  repository for the Procedure entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long> {}
