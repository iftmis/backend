package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.FileResource;

/**
 * Spring Data  repository for the FileResource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileResourceRepository extends JpaRepository<FileResource, Long> {}
