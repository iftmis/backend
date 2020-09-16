package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Risk;
import org.tamisemi.iftmis.repository.RiskRepository;
import org.tamisemi.iftmis.service.dto.RiskDTO;
import org.tamisemi.iftmis.service.mapper.RiskMapper;

/**
 * Service Implementation for managing {@link Risk}.
 */
@Service
@Transactional
public class RiskService {
    private final Logger log = LoggerFactory.getLogger(RiskService.class);

    private final RiskRepository riskRepository;

    private final RiskMapper riskMapper;

    public RiskService(RiskRepository riskRepository, RiskMapper riskMapper) {
        this.riskRepository = riskRepository;
        this.riskMapper = riskMapper;
    }

    /**
     * Save a risk.
     *
     * @param riskDTO the entity to save.
     * @return the persisted entity.
     */
    public RiskDTO save(RiskDTO riskDTO) {
        Risk risk = riskMapper.toEntity(riskDTO);
        Risk finalRisk = risk;
        risk.getRiskRatings().forEach(r -> r.setRisk(finalRisk));
        risk = riskRepository.save(risk);
        return riskMapper.toDto(risk);
    }

    /**
     * Get all the risks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Risks");
        return riskRepository.findAll(pageable).map(riskMapper::toDto);
    }

    /**
     * Get one risk by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RiskDTO> findOne(Long id) {
        log.debug("Request to get Risk : {}", id);
        return riskRepository.findById(id).map(riskMapper::toDto);
    }

    /**
     * Delete the risk by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Risk : {}", id);
        riskRepository.deleteById(id);
    }

    /**
     * @param organisationUnitId
     * @param riskRegisterId
     * @return
     */
    public List<RiskDTO> findAllByOrganisationIdAndRiskRegisterId(Long organisationUnitId, Long riskRegisterId) {
        return riskRepository.findAllByOrganisationIdAndRiskRegisterId(organisationUnitId, riskRegisterId).stream()
            .map(riskMapper::toDto)
            .collect(Collectors.toList());
    }

    /**
     *
     * @param organisationUnitId
     * @param financialYearId
     * @return
     */
    public List<RiskDTO> findAllByOrganisationIdAndFinancialYearId(Long organisationUnitId, Long financialYearId) {
        return riskRepository.findAllByOrganisationIdAndFinancialYearId(organisationUnitId, financialYearId).stream()
            .map(riskMapper::toDto)
            .collect(Collectors.toList());
    }

    /**
     * @param organisationUnitId
     * @param riskRegisterId
     * @param pageable
     * @return
     */
    public Page<RiskDTO> findAllByOrganisationIdAndRiskRegisterId(Long organisationUnitId, Long riskRegisterId, Pageable pageable) {
        return riskRepository.findAllByOrganisationIdAndRiskRegisterId(organisationUnitId, riskRegisterId, pageable).map(riskMapper::toDto);
    }

    public Page<RiskDTO> findAllByOrganisationIdAndFinancialYearId(Long organisationUnitId, Long financialYearId, Pageable pageable) {
        return riskRepository.findAllByOrganisationIdAndFinancialYearId(organisationUnitId, financialYearId, pageable).map(riskMapper::toDto);
    }
}
