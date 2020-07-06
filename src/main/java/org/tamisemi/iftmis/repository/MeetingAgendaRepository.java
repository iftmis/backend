package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.tamisemi.iftmis.domain.MeetingAgenda;

/**
 * Spring Data  repository for the MeetingAgenda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingAgendaRepository extends JpaRepository<MeetingAgenda, Long> {}
