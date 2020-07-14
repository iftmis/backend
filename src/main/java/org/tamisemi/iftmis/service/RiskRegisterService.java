package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.RiskRegister;
import org.tamisemi.iftmis.repository.RiskRegisterRepository;
import org.tamisemi.iftmis.service.dto.RiskRegisterDTO;
import org.tamisemi.iftmis.service.mapper.RiskRegisterMapper;

/**
 * Service Implementation for managing {@link RiskRegister}.
 */
@Service
@Transactional
public class RiskRegisterService {
    private final Logger log = LoggerFactory.getLogger(RiskRegisterService.class);

    private final RiskRegisterRepository riskRegisterRepository;

    private final RiskRegisterMapper riskRegisterMapper;

    public RiskRegisterService(RiskRegisterRepository riskRegisterRepository, RiskRegisterMapper riskRegisterMapper) {
        this.riskRegisterRepository = riskRegisterRepository;
        this.riskRegisterMapper = riskRegisterMapper;
    }

    /**
     * Save a riskRegister.
     *
     * @param riskRegisterDTO the entity to save.
     * @return the persisted entity.
     */
    public RiskRegisterDTO save(RiskRegisterDTO riskRegisterDTO) {
        log.debug("Request to save RiskRegister : {}", riskRegisterDTO);
        RiskRegister riskRegister = riskRegisterMapper.toEntity(riskRegisterDTO);
        riskRegister = riskRegisterRepository.save(riskRegister);
        return riskRegisterMapper.toDto(riskRegister);
    }

    /**
     * Get all the riskRegisters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskRegisterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskRegisters");
        return riskRegisterRepository.findAll(pageable).map(riskRegisterMapper::toDto);
    }

    /**
     * Get one riskRegister by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RiskRegisterDTO> findOne(Long id) {
        log.debug("Request to get RiskRegister : {}", id);
        return riskRegisterRepository.findById(id).map(riskRegisterMapper::toDto);
    }

    /**
     * Delete the riskRegister by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskRegister : {}", id);
        riskRegisterRepository.deleteById(id);
    }
}
