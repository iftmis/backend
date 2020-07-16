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
import org.tamisemi.iftmis.domain.RiskRank;
import org.tamisemi.iftmis.repository.RiskRankRepository;
import org.tamisemi.iftmis.service.dto.RiskRankDTO;
import org.tamisemi.iftmis.service.mapper.RiskRankMapper;

/**
 * Service Implementation for managing {@link RiskRank}.
 */
@Service
@Transactional
public class RiskRankService {
    private final Logger log = LoggerFactory.getLogger(RiskRankService.class);

    private final RiskRankRepository riskRankRepository;

    private final RiskRankMapper riskRankMapper;

    public RiskRankService(RiskRankRepository riskRankRepository, RiskRankMapper riskRankMapper) {
        this.riskRankRepository = riskRankRepository;
        this.riskRankMapper = riskRankMapper;
    }

    /**
     * Save a riskRank.
     *
     * @param riskRankDTO the entity to save.
     * @return the persisted entity.
     */
    public RiskRankDTO save(RiskRankDTO riskRankDTO) {
        log.debug("Request to save RiskRank : {}", riskRankDTO);
        RiskRank riskRank = riskRankMapper.toEntity(riskRankDTO);
        riskRank = riskRankRepository.save(riskRank);
        return riskRankMapper.toDto(riskRank);
    }

    /**
     * Get all the riskRanks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskRankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskRanks");
        return riskRankRepository.findAll(pageable).map(riskRankMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<RiskRankDTO> findAll() {
        return riskRankRepository.findAll().stream().map(riskRankMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get one riskRank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RiskRankDTO> findOne(Long id) {
        log.debug("Request to get RiskRank : {}", id);
        return riskRankRepository.findById(id).map(riskRankMapper::toDto);
    }

    /**
     * Delete the riskRank by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskRank : {}", id);
        riskRankRepository.deleteById(id);
    }
}
