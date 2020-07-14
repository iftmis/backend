package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tamisemi.iftmis.domain.Authority;
import org.tamisemi.iftmis.repository.AuthorityRepository;
import org.tamisemi.iftmis.service.IndicatorService;
import org.tamisemi.iftmis.service.dto.AuthorityDTO;
import org.tamisemi.iftmis.service.mapper.AuthorityMapper;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final Logger log = LoggerFactory.getLogger(IndicatorService.class);
    private final AuthorityMapper authorityMapper;

    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public Page<Authority> findAll(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

    public void delete(String name) {
        authorityRepository.deleteByName(name);
    }

    public Authority save(AuthorityDTO authorityDTO) {
        Authority authority = authorityMapper.toEntity(authorityDTO);
        return authorityRepository.save(authority);
    }

    public Optional<Authority> findByName(String name) {
        return authorityRepository.findFirstByName(name);
    }
}
