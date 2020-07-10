package org.tamisemi.iftmis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tamisemi.iftmis.domain.Authority;
import org.tamisemi.iftmis.service.dto.AuthorityDTO;

import java.util.List;
import java.util.Optional;

@Service
public interface AuthorityService {
    List<Authority> findAll();
    Page<Authority> findAll(Pageable pageable);
    void delete(String name);
    Authority save(AuthorityDTO authority);
    Optional<Authority> findByName(String name);
}
