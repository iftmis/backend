package org.tamisemi.iftmis.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.tamisemi.iftmis.domain.Authority;
import org.tamisemi.iftmis.domain.Indicator;
import org.tamisemi.iftmis.repository.AuthorityRepository;
import org.tamisemi.iftmis.service.AuthorityService;
import org.tamisemi.iftmis.service.IndicatorService;
import org.tamisemi.iftmis.service.dto.AuthorityDTO;
import org.tamisemi.iftmis.service.dto.IndicatorDTO;
import org.tamisemi.iftmis.service.mapper.AuthorityMapper;
import org.tamisemi.iftmis.service.mapper.IndicatorMapper;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImp implements AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final Logger log = LoggerFactory.getLogger(IndicatorService.class);
    private final AuthorityMapper authorityMapper;

    public AuthorityServiceImp(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    @Override
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    @Override
    public Page<Authority> findAll(Pageable pageable) {
        return authorityRepository.findAll(pageable);
    }

    @Override
    public void delete(String name) {
            authorityRepository.deleteByName(name);
    }

    @Override
    public Authority save(AuthorityDTO authorityDTO) {
        Authority authority = authorityMapper.toEntity(authorityDTO);
        return authorityRepository.save(authority);
    }

    @Override
    public Optional<Authority> findByName(String name) {
        return authorityRepository.findFirstByName(name);
    }
}
