package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.ResponseAttachment;

/**
 * Spring Data  repository for the ResponseAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResponseAttachmentRepository extends JpaRepository<ResponseAttachment, Long> {}
