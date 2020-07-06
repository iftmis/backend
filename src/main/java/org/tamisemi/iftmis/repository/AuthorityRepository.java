package org.tamisemi.iftmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tamisemi.iftmis.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
