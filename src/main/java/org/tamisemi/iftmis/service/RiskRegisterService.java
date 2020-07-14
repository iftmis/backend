package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * @return
     */
    @Transactional(readOnly = true)
    public List<RiskRegisterDTO> findAll() {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAll().stream().map(riskRegisterMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param financialYearId
     * @return
     */
    @Transactional(readOnly = true)
    public List<RiskRegisterDTO> findAllByFinancialYearId(Long financialYearId) {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAllByFinancialYearId(financialYearId).stream().map(riskRegisterMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param organisationUnitId
     * @return
     */
    @Transactional(readOnly = true)
    public List<RiskRegisterDTO> findAllByOrganisationUnitId(Long organisationUnitId) {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAllByOrganisationUnitId(organisationUnitId).stream().map(riskRegisterMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param financialYearId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<RiskRegisterDTO> findAllByFinancialYearId(Long financialYearId, Pageable pageable) {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAllByFinancialYearId(financialYearId, pageable).map(riskRegisterMapper::toDto);
    }

    /**
     *
     * @param organisationUnitId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<RiskRegisterDTO> findAllByOrganisationUnitId(Long organisationUnitId, Pageable pageable) {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAllByOrganisationUnitId(organisationUnitId, pageable).map(riskRegisterMapper::toDto);
    }

    /**
     * @param financialYearId
     * @param organisationUnitId
     * @return
     */
    @Transactional(readOnly = true)
    public List<RiskRegisterDTO> findAllByFinancialYearIdAndOrganisationUnitId(Long financialYearId, Long organisationUnitId) {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAllByFinancialYearIdAndOrganisationUnitId(financialYearId, organisationUnitId).stream().map(riskRegisterMapper::toDto).collect(Collectors.toList());
    }

    /**
     * @param financialYearId
     * @param organisationUnitId
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<RiskRegisterDTO> findAllByFinancialYearIdAndOrganisationUnitId(Long financialYearId, Long organisationUnitId, Pageable pageable) {
        log.debug("Request to get all Risk Registers");
        return riskRegisterRepository.findAllByFinancialYearIdAndOrganisationUnitId(financialYearId, organisationUnitId, pageable).map(riskRegisterMapper::toDto);
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
