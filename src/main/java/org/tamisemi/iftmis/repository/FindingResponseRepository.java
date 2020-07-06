package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FindingResponse;

/**
 * Spring Data  repository for the FindingResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FindingResponseRepository extends JpaRepository<FindingResponse, Long> {}
