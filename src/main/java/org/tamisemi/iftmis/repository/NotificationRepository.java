package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.Notification;

/**
 * Spring Data  repository for the Notification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {}
