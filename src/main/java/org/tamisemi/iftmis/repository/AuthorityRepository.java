package org.tamisemi.iftmis.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tamisemi.iftmis.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    void deleteByName(String name);
    Optional<Authority> findFirstByName(String name);
}
